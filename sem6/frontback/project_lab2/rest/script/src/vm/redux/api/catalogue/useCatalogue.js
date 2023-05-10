import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";

import {
  setCatalogueTableData,
  updateCatalogueLoaded,
  resetCatalogueSelectedRowsIdx,
  addCatalogueSelectedRowsIdx,
  delCatalogueSelectedRowsIdx,
} from "vm/redux/impl/slice";

import { getItems } from "model/main/catalogueItems/modelCatalogue";
import { addPageCartItems } from "model/main/itemCart/modelItemCart";

const fetchCatalogue = (itemType) => async (dispatch) => {
  if (!itemType) {
    return;
  }
  try {
    const data = await getItems(itemType);
    dispatch(setCatalogueTableData(data));
    dispatch(updateCatalogueLoaded(true));
  } catch (error) {
    console.error(error);
  }
};

function useCatalogue() {
  const dispatch = useDispatch();
  const { itemType, tableData, loaded, selectedRowsIdx } = useSelector(
    (state) => state.catalogue
  );

  useEffect(() => {
    dispatch(fetchCatalogue(itemType));
    return () => {
      dispatch(resetCatalogueSelectedRowsIdx());
    };
  }, [dispatch, itemType]);

  const addToSelectedRows = (index) => {
    dispatch(addCatalogueSelectedRowsIdx(index));
  };

  const delFromSelectedRows = (index) => {
    dispatch(delCatalogueSelectedRowsIdx(index));
  };

  const handleAddingitems = () => {
    const itemsToAdd = [];
    for (let index of selectedRowsIdx) {
      let dataToAdd = { ...tableData[index] };
      dataToAdd.itemType = itemType;
      itemsToAdd.push(dataToAdd);
    }
    addPageCartItems(itemsToAdd);
  };

  return {
    tableData,
    loaded,
    selectedRowsIdx,
    addToSelectedRows,
    delFromSelectedRows,
    handleAddingitems,
  };
}

export { useCatalogue };