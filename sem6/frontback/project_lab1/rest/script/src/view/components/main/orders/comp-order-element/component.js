import React from "react";

class CompOrderElement extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <div className="itemCheckBoxDiv">
          <input
            className="itemCheckBox"
            type="checkbox"
            checked={this.props.checked}
            onChange={this.props.onChangeCheck}
          ></input>
        </div>

        <div className="itemImage"></div>

        <div className="itemContent">
          <div className="itemDescription">
            <div className="itemDescriptionText">
              {this.props.orderData}
              <br></br>
              Item: {this.props.itemData}
            </div>
            <div className="itemDescriptionData">
              Item id: {this.props.itemid}
              Order id: {this.props.orderid}
            </div>
          </div>
          <div className="itemPrice">
            <span> {this.props.price} P</span>
          </div>
        </div>
      </div>
    );
  }
}

export default CompOrderElement;
