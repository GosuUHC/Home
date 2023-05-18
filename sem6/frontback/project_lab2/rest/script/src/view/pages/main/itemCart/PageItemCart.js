import Button from "view/components/common/Button";

import { TableElement } from "model/main/modelMain";
import CompItemCartElement from "view/components/main/itemCart/item-cart-element/ItemCartElement";

import styles from "./PageItemCart.module.css";
import { useItemCart } from "vm/api";
import Loading from "view/components/common/Loading";

function PageItemCart(props) {
  const {
    itemsList,
    loaded,
    checks,
    counts,
    handleCheck,
    handleCount,
    handleDelete,
    handleOrderCreation,
  } = useItemCart();

  if (!loaded) {
    return <Loading />;
  }
  const renderItemCart = itemsList.map((pageCartItem, i) => {
    const item = new TableElement();
    item.set(pageCartItem);
    return (
      <CompItemCartElement
        key={i}
        itemDesc={item.getRemainingFields(["id", "price"], true)}
        itemid={item.getField("id")}
        price={item.getField("price")}
        count={counts[i]}
        checked={checks[i]}
        onChangeCheck={(e) => handleCheck(i, e.target.checked)}
        onChangeCount={(e) => handleCount(i, e.target.value)}
      ></CompItemCartElement>
    );
  });

  return (
    <div>
      <div className={styles.delBtnDiv} onClick={handleDelete}>
        Delete selected
      </div>
      <Button name="Add to orders" onClick={handleOrderCreation}></Button>
      {renderItemCart}
    </div>
  );
}

export default PageItemCart;
