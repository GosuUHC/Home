import React from "react";

import "./styles.css";

class CompItemCartElement extends React.Component {
  constructor(props) {
    super(props);
  }

  calcPrice = () => {
    return this.props.count * Number(this.props.price);
  };

  render() {
    return (
      <div className="mainTableDivItem">
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
            <div className="itemDescriptionText">{this.props.itemDesc}</div>
            <div className="itemDescriptionData">
              Item id: {this.props.itemid}
            </div>
          </div>

          <div className="itemPrice">
            <span> {this.calcPrice()} P</span>
          </div>

          <article className="itemActions">
            <div className="itemCountAction">
              <input
                className="itemCountValue"
                type="number"
                min="1"
                max="10"
                value={this.props.count}
                onChange={this.props.onChangeCount}
              ></input>
            </div>
          </article>
        </div>
      </div>
    );
  }
}

export default CompItemCartElement;
