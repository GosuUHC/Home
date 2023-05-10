import { useDispatch, useSelector } from "react-redux";

import { setItemType } from "vm/redux/impl/slice";

function useItemType() {
  const dispatch = useDispatch();
  const { itemType } = useSelector((state) => state.catalogue.itemType);

  const handleItemTypeChange = (value) => {
    value = value ? value : "";
    dispatch(setItemType(value));
  };

  return { handleItemTypeChange, itemType };
}

export { useItemType };
