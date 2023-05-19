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

  const renderOrders = tableData.map((order, i) => {
    const item = order.item;
    const otherFields = Object.entries(item.other)
      .map(([key, value]) => `${key}: ${value}`)
      .join(", ");

    return (
      <ProductCardWithCheckbox
        key={i}
        title={`${item.manufacturer} ${item.name}`}
        description={[
          otherFields,
          `order id: ${order.id}`,
          `count: ${order.itemCount}`,
          `type: ${order.itemType}`,
          `item id: ${order.itemId}`,
          `status: ${order.status}`,
        ]}
        price={order.price}
        img={item.img}
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
