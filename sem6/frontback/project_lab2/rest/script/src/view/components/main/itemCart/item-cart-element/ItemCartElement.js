import React from "react";
import styles from "./ItemCartElement.module.css";

const ItemCartElement = (props) => {
  const calcPrice = () => {
    return props.count * Number(props.price);
  };

  return (
    <div className={styles.mainTableDivItem}>
      <div className="itemCheckBoxDiv">
        <input
          className="itemCheckBox"
          type="checkbox"
          checked={props.checked}
          onChange={props.onChangeCheck}
        ></input>
      </div>

      <div className="itemImage"></div>

      <div className={styles.itemContent}>
        <div className="itemDescription">
          <div className="itemDescriptionText">{props.itemDesc}</div>
          <div className="itemDescriptionData">Item id: {props.itemid}</div>
        </div>

        <div className="itemPrice">
          <span> {calcPrice()} P</span>
        </div>

        <article className="itemActions">
          <div className="itemCountAction">
            <input
              className={styles.itemCountValue}
              type="number"
              min="1"
              max="10"
              value={props.count}
              onChange={props.onChangeCount}
            ></input>
          </div>
        </article>
      </div>
    </div>
  );
};

export default ItemCartElement;
