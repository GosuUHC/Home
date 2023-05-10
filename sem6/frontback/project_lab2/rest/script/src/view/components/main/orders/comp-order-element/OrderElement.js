import React from "react";

function OrderElement(props) {
  return (
    <div>
      <div className="itemCheckBoxDiv">
        <input
          className="itemCheckBox"
          type="checkbox"
          checked={props.checked}
          onChange={props.onChangeCheck}
        ></input>
      </div>

      <div className="itemImage"></div>

      <div className="itemContent">
        <div className="itemDescription">
          <div className="itemDescriptionText">
            {props.orderData}
            <br></br>
            Item: {props.itemData}
          </div>
          <div className="itemDescriptionData">
            Item id: {props.itemid}
            Order id: {props.orderid}
          </div>
        </div>
        <div className="itemPrice">
          <span> {props.price} P</span>
        </div>
      </div>
    </div>
  );
}

export default OrderElement;
