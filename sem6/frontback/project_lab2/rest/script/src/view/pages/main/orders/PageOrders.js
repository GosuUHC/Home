import { CardElement } from "model/main/modelMain.js";
import { useOrders } from "vm/api";

import styles from "./PageOrders.module.css";
import Loading from "view/components/common/Loading";
import ProductCardWithCheckbox from "view/components/main/shared/ProductCardWithCheckBox/ProductCardWithCheckBox";

function PageOrders(props) {
  const {
    tableData,
    loaded,
    checks,
    handleCheck,
    handleDelete,
    handleChangeAll,
  } = useOrders();

  if (!loaded) {
    return <Loading />;
  }

  const renderOrders = tableData.map((tableItem, i) => {
    const order = new CardElement();
    const item = new CardElement();
    order.set(tableItem);
    item.set(tableItem["item"]);
    return (
      <ProductCardWithCheckbox
        key={i}
        title={item.getRemainingFields(["id", "img", "price"], true)}
        description={order.getRemainingFields(["userLogin", "item", "price"])}
        price={order.getField("price")}
        img={item.getField("img")}
        checked={checks[i]}
        handleCheckboxChange={(e) => handleCheck(i, e.target.checked)}
      />
    );
  });

  return (
    <div className={styles.pageContainer}>
      <div className={styles.buttonContainer}>
        <div className={styles.selectAllButton} onClick={handleChangeAll}>
          Select all
        </div>
        <div className={styles.deleteSelectedButton} onClick={handleDelete}>
          Delete selected
        </div>
      </div>
      <div className={styles.ordersContainer}>{renderOrders}</div>
    </div>
  );
}

export default PageOrders;
