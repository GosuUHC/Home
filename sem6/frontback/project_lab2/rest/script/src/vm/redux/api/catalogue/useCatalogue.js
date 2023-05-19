import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import {
  addAddedToCartIndices,
  resetAddedToCartIndices,
  setCatalogueTableData,
  updateCatalogueLoaded,
} from "vm/redux/impl/slice";

import { getItems } from "model/main/catalogueItems/modelCatalogue";
import { addPageCartItems } from "model/main/itemCart/modelItemCart";

const fetchCatalogue = (itemType) => async (dispatch) => {
  if (!itemType || itemType === "") {
    return;
  }
  try {
    const data = await getItems(itemType);
    dispatch(resetAddedToCartIndices());
    dispatch(setCatalogueTableData(data));
    dispatch(updateCatalogueLoaded(true));
  } catch (error) {
    console.error(error);
  }
};

function useCatalogue() {
  const dispatch = useDispatch();
  const { itemType, tableData, loaded, addedToCartIndices } = useSelector(
    (state) => state.catalogue
  );

  useEffect(() => {
    dispatch(fetchCatalogue(itemType));
  }, [dispatch, itemType]);

  const handleAdd = (index) => {
    const itemsToAdd = [];
    let dataToAdd = { ...tableData[index] };
    dataToAdd.itemType = itemType;
    itemsToAdd.push(dataToAdd);
    addPageCartItems(itemsToAdd);
    dispatch(addAddedToCartIndices(index));
  };

  return {
    tableData,
    loaded,
    addedToCartIndices,
    handleAdd,
  };
}

export { useCatalogue };
