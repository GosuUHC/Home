import { TableElement } from "model/main/modelMain.js";
import OrderElement from "view/components/main/orders/comp-order-element/OrderElement.js";
import { useOrders } from "vm/api";

import styles from "./PageOrders.module.css";

function PageOrders(props) {
  const { tableData, loaded, checks, handleCheck, handleDelete } = useOrders();

  if (!loaded) {
    return <div>Loading...</div>;
  }

  const renderOrders = tableData.map((tableItem, i) => {
    const order = new TableElement();
    const item = new TableElement();
    order.set(tableItem);
    item.set(tableItem["item"]);
    return (
      <OrderElement
        key={i}
        orderData={order.getRemainingFields([
          "id",
          "itemid",
          "userLogin",
          "item",
        ])}
        itemData={item.getRemainingFields(["id"], true)}
        itemid={item.getField("id")}
        orderid={order.getField("id")}
        price={order.getField("price")}
        checked={checks[i]}
        onChangeCheck={(e) => handleCheck(i, e.target.checked)}
      ></OrderElement>
    );
  });
  return (
    <div>
      <div className={styles.delBtnDiv} onClick={handleDelete}>
        Delete selected
      </div>
      {renderOrders}
    </div>
  );
}

export default PageOrders;
