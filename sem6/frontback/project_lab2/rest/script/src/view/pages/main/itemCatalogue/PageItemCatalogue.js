import ItemCatalogue from "view/components/main/itemCatalogue/comp-item-catalogue/ItemCatalogue.js";
import Button from "view/components/common/button";

import styles from "./PageItemCatalogue.module.css";
import { useCatalogue } from "vm/api.js";

function PageItemCatalogue(props) {
  const {
    tableData,
    loaded,
    addToSelectedRows,
    delFromSelectedRows,
    handleAddingitems
  } = useCatalogue();

  const handleClick = (e) => {
    const dataIndex = e.currentTarget.rowIndex - 1;
    const action = e.currentTarget.classList.contains(styles.active)
      ? () => {
          e.currentTarget.classList.remove(styles.active);
          delFromSelectedRows(dataIndex);
        }
      : () => {
          e.currentTarget.classList.add(styles.active);
          addToSelectedRows(dataIndex);
        };

    action();
  };

  if (!loaded) {
    return <div />;
  }
  
  return (
    <div>
      <Button name="Add to cart" onClick={handleAddingitems}></Button>
      <ItemCatalogue
        tableData={tableData}
        handleClick={handleClick}
      ></ItemCatalogue>
    </div>
  );
}

export default PageItemCatalogue;
