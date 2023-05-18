import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import {
  resetSelectAllValue,
  reverseChecks,
  setOrdersChecks,
  setOrdersTableData,
  updateOrdersCheck,
  updateOrdersLoaded,
} from "vm/redux/impl/slice";

import { deleteOrder, getAllOrders } from "model/main/orders/modelOrders";

const fetchOrders = () => async (dispatch) => {
  try {
    const data = await getAllOrders();
    const arrLen = data.length;
    const checksArr = Array(arrLen).fill(false);
    dispatch(setOrdersTableData(data));
    dispatch(setOrdersChecks(checksArr));
    dispatch(updateOrdersLoaded(true));
  } catch (error) {
    console.error(error);
  }
};

function useOrders() {
  const dispatch = useDispatch();
  const { tableData, loaded, checks } = useSelector((state) => state.orders);

  useEffect(() => {
    dispatch(fetchOrders());
    return () => {
      dispatch(resetSelectAllValue());
    };
  }, [dispatch]);

  const handleCheck = (index, value) => {
    dispatch(updateOrdersCheck({ index, value }));
  };

  const handleDelete = async () => {
    for (let i = 0; i < tableData.length; i++) {
      if (checks[i]) {
        const orderid = tableData[i]["id"];
        await deleteOrder(orderid);
      }
    }
    dispatch(fetchOrders());
    dispatch(resetSelectAllValue());
  };

  const handleChangeAll = () => {
    dispatch(reverseChecks());
  };

  return {
    tableData,
    loaded,
    checks,
    handleCheck,
    handleDelete,
    handleChangeAll,
  };
}

export { useOrders };
