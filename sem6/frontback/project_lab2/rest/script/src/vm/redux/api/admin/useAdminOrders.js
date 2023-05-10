import { useDispatch, useSelector } from "react-redux";
import {
  getAllOrdersPaged,
  getAllOrdersPagedByUsername,
  updateOrderStatus,
  pageSize,
} from "model/admin/modelAdmin";
import {
  manageOrders,
  resetChangedOrders,
  setAdminPageNumber,
  setAdminTableData,
  updateAdminLoaded,
  setTotalCount,
} from "vm/redux/impl/slice";
import { useEffect } from "react";

const fetchPagedOrders = (page) => async (dispatch) => {
  try {
    const data = await getAllOrdersPaged(page);
    dispatch(setAdminTableData(data.orders));
    dispatch(setTotalCount(data.totalCount));
    dispatch(updateAdminLoaded(true));
  } catch (error) {
    console.error(error);
  }
};

const fetchPagedOrdersByUsername = (username, page) => async (dispatch) => {
  try {
    const data = await getAllOrdersPagedByUsername(username, page);
    dispatch(setAdminTableData(data.orders));
    dispatch(updateAdminLoaded(true));
  } catch (error) {
    console.error(error);
  }
};

function useAdminOrders() {
  const dispatch = useDispatch();
  const {
    tableData,
    loaded,
    pageNumber,
    ordersIdAndUpdatedStatus,
    totalCount,
  } = useSelector((state) => state.admin);

  useEffect(() => {
    dispatch(fetchPagedOrders(pageNumber));
  }, [dispatch, pageNumber]);

  const handlePageNumberChange = (e, value) => {
    dispatch(setAdminPageNumber(value));
  };

  const handleOrderStatusChange = (orderid, newStatus) => {
    if (newStatus !== "") {
      dispatch(manageOrders({ orderid, newStatus }));
    }
  };

  const handleOrderStatusChangeConfirm = async () => {
    await Promise.all(
      ordersIdAndUpdatedStatus.map((order) =>
        updateOrderStatus(order.id, order.newStatus)
      )
    );

    dispatch(resetChangedOrders());
    dispatch(fetchPagedOrders(pageNumber));
  };

  return {
    tableData,
    loaded,
    pageNumber,
    pageSize,
    totalCount,
    handlePageNumberChange,
    handleOrderStatusChange,
    handleOrderStatusChangeConfirm,
  };
}

export { useAdminOrders };
