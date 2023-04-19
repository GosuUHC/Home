import React from "react";
import { TableElement } from "../../../../model/main/modelMain.js";
import {
  getAllOrders,
  deleteOrder,
} from "../../../../model/main/orders/modelOrders.js";
import Button from "../../../components/common/button.js";
import CompOrderElement from "../../../components/main/orders/comp-order-element/component.js";

class CompPageOrders extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tableData: undefined,
      loaded: false,
      checks: [],
    };
  }

  _fillOrders() {
    getAllOrders().then((tableData2) => {
      const arrLen = tableData2.length;
      const checks = Array(arrLen).fill(false);
      this.setState({ tableData: tableData2, loaded: true, checks });
    });
  }

  componentDidMount() {
    this._fillOrders();
  }

  onChangeCheck = (idx) => {
    this.setState((PrevState) => {
      let checks = PrevState.checks;
      checks[idx] = !checks[idx];
      return {
        checks,
      };
    });
  };

  _deleteOrder = () => {
    this.state.tableData.forEach(async (elem, i) => {
      if (!this.state.checks[i]) {
        return;
      }

      const orderid = elem["id"];
      await deleteOrder(orderid);
    });
    this._fillOrders();
  };

  render() {
    if (!this.state.loaded) {
      return <div></div>;
    }
    const renderOrders = this.state.tableData.map((tableItem, i) => {
      let order = new TableElement();
      let item = new TableElement();
      order.set(tableItem);
      item.set(tableItem["item"]);
      return (
        <CompOrderElement
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
          checked={this.state.checks[i]}
          onChangeCheck={() => this.onChangeCheck(i)}
        ></CompOrderElement>
      );
    });
    return (
      <div>
        <div className="delBtnDiv" onClick={this._deleteOrder}>
          Delete selected
        </div>
        {renderOrders}
      </div>
    );
  }
}

export default CompPageOrders;
