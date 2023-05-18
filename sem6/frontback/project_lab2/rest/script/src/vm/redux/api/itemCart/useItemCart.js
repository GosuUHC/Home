import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import {
  getPageCartItemsList,
  deletePageCartItems,
} from "model/main/itemCart/modelItemCart";
import {
  addItems,
  setItemsCounts,
  updateItemsLoaded,
  updateItemsCount,
} from "vm/redux/impl/slice";
import { addOrder } from "model/main/orders/modelOrders";

const fetchItemCart = () => async (dispatch) => {
  try {
    const data = await getPageCartItemsList();
    const arrLen = data.length;
    const counts = Array(arrLen).fill(1);
    dispatch(addItems(data));
    dispatch(setItemsCounts(counts));
    dispatch(updateItemsLoaded(true));
  } catch (error) {
    console.error(error);
  }
};

function useItemCart() {
  const dispatch = useDispatch();
  const { itemsList, loaded, counts } = useSelector((state) => state.itemCart);

  useEffect(() => {
    dispatch(fetchItemCart());
  }, [dispatch]);

  const handleCount = (index, value) => {
    if (value < 1 || value > 10) {
      return;
    }
    dispatch(updateItemsCount({ index, value }));
  };

  const handleDelete = async (index) => {
    const indicesToDel = [index];
    await deletePageCartItems(indicesToDel);
    dispatch(fetchItemCart());
  };

  const handleOrderCreation = async (i) => {
    const itemCount = counts[i];
    const itemId = itemsList[i]["id"];
    const itemType = itemsList[i]["itemType"];
    try {
      await addOrder(itemId, itemType, itemCount);
      return true;
    } catch (error) {
      console.error(`Error creating order for item ${itemId}: ${error}`);
      return false;
    }
  };

  return {
    itemsList,
    loaded,
    counts,
    handleCount,
    handleDelete,
    handleOrderCreation,
  };
}

export { useItemCart };
