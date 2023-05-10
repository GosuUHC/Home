import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import {
  getPageCartItemsList,
  deletePageCartItems,
} from "model/main/itemCart/modelItemCart";
import {
  addItems,
  setItemsChecks,
  setItemsCounts,
  updateItemsLoaded,
  updateItemsCheck,
  updateItemsCount,
} from "vm/redux/impl/slice";
import { addOrder } from "model/main/orders/modelOrders";

const fetchItemCart = () => async (dispatch) => {
  try {
    const data = await getPageCartItemsList();
    const arrLen = data.length;
    const checks = Array(arrLen).fill(false);
    const counts = Array(arrLen).fill(1);
    dispatch(addItems(data));
    dispatch(setItemsChecks(checks));
    dispatch(setItemsCounts(counts));
    dispatch(updateItemsLoaded(true));
  } catch (error) {
    console.error(error);
  }
};

function useItemCart() {
  const dispatch = useDispatch();
  const { itemsList, loaded, checks, counts } = useSelector(
    (state) => state.itemCart
  );
  useEffect(() => {
    dispatch(fetchItemCart());
  }, [dispatch]);

  const handleCheck = (index, value) => {
    dispatch(updateItemsCheck({ index, value }));
  };

  const handleCount = (index, value) => {
    dispatch(updateItemsCount({ index, value }));
  };

  const handleDelete = async () => {
    const indicesToDel = checks
      .map((elem, idx) => (elem ? idx : ""))
      .filter(String); // make an array of checked items idxes
    await deletePageCartItems(indicesToDel);
    dispatch(fetchItemCart());
  };

  const handleOrderCreation = () => {
    itemsList.forEach(async (item, i) => {
      if (!checks[i]) {
        return;
      }
      const itemCount = counts[i];
      const itemid = item["id"];
      const itemType = item["itemType"];
      await addOrder(itemid, itemType, itemCount);
    });
  };

  return {
    itemsList,
    loaded,
    checks,
    counts,
    handleCheck,
    handleCount,
    handleDelete,
    handleOrderCreation,
  };
}

export { useItemCart };
