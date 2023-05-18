import { TableElement } from "model/main/modelMain.js";
import { useOrders } from "vm/api";

import styles from "./PageOrders.module.css";
import ProductCard from "view/components/main/shared/ProductCard/ProductCard";
import Loading from "view/components/common/Loading";

function PageOrders(props) {
  const { tableData, loaded, checks, handleCheck, handleDelete } = useOrders();

  if (!loaded) {
    return <Loading />
  }
  
  const renderOrders = tableData.map((tableItem, i) => {
    const order = new TableElement();
    const item = new TableElement();
    order.set(tableItem);
    item.set(tableItem["item"]);
    return (
      <ProductCard
        key={i}
        title={item.getRemainingFields(["id", "img", "price"], true)}
        description={order.getRemainingFields([
          "userLogin",
          "item",
          "price",
        ])}
        price={order.getField("price")}
        img={item.getField("img")}
        // checked={checks[i]}
        // onChangeCheck={(e) => handleCheck(i, e.target.checked)}
      ></ProductCard>
    );
  });
  return (
    <div className={styles.pageContainer}>
      <div className={styles.delBtnDiv} onClick={handleDelete}>
        Delete selected
      </div>
      <div className={styles.ordersContainer}>{renderOrders}</div>
    </div>
  );
}

export default PageOrders;
