import React from "react";
import Button from "../../../components/common/button";

import { TableElement } from "../../../../model/main/modelMain.js";
import { addOrder } from "../../../../model/main/orders/modelOrders.js";
import {
  getPageCartItemsList,
  deletePageCartItems,
} from "../../../../model/main/itemCart/modelItemCart.js";
import CompItemCartElement from "../../../components/main/itemCart/comp-item-cart-element/component";

import "./styles.css";

class CompPageItemCart extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      itemsList: undefined,
      loaded: false,
      checks: [],
      counts: [],
    };
  }

  componentDidMount() {
    getPageCartItemsList().then((pageCartItems) => {
      const arrLen = pageCartItems.length;
      const checks = Array(arrLen).fill(false);
      const counts = Array(arrLen).fill(1);
      this.setState({ itemsList: pageCartItems, loaded: true, checks, counts });
    });
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

  onChangeCount = (e, idx) => {
    this.setState((PrevState) => {
      let counts = PrevState.counts;
      counts[idx] = e.target.value;
      return {
        counts,
      };
    });
  };

  _deleteSelectedItems = () => {
    let indicesToDel = this.state.checks
      .map((elem, idx) => (elem ? idx : ""))
      .filter(String); // make an array of checked items idxes
    deletePageCartItems(indicesToDel).then(() => this.componentDidMount());
  };

  _collectOrder = () => {
    this.state.itemsList.forEach(async (item, i) => {
      if (!this.state.checks[i]) {
        return;
      }
      const itemCount = this.state.counts[i];
      const itemid = item["id"];
      const itemType = item["itemType"];
      await addOrder(itemid, itemType, itemCount);
    });
  };

  render() {
    if (!this.state.loaded) {
      return <div></div>;
    }
    const renderItemCart = this.state.itemsList.map((pageCartItem, i) => {
      let item = new TableElement();
      item.set(pageCartItem);
      return (
        <CompItemCartElement
          key={i}
          itemDesc={item.getRemainingFields(["id", "price"], true)}
          itemid={item.getField("id")}
          price={item.getField("price")}
          count={this.state.counts[i]}
          checked={this.state.checks[i]}
          onChangeCheck={() => this.onChangeCheck(i)}
          onChangeCount={(e) => this.onChangeCount(e, i)}
        ></CompItemCartElement>
      );
    });

    return (
      <div>
        <div className="delBtnDiv" onClick={this._deleteSelectedItems}>
          Delete selected
        </div>
        <Button name="Add to orders" onClick={this._collectOrder}></Button>
        {renderItemCart}
      </div>
    );
  }
}

export default CompPageItemCart;
