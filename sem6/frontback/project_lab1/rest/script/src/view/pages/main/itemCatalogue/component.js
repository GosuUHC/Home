import React from "react";

import "../../../components/main/itemCatalogue/comp-item-catalogue/component.js";
import { getItems } from "../../../../model/main/catalogueItems/modelCatalogue.js";
import CompItemCatalogue from "../../../components/main/itemCatalogue/comp-item-catalogue/component.js";
import Button from "../../../components/common/button.js";
import { addPageCartItems } from "../../../../model/main/itemCart/modelItemCart.js";

import "./styles.css";

class CompPageItemCatalogue extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tableData: undefined,
      loaded: false,
      selectedRowsIdx: [],
    };
  }

  componentDidMount() {
    if (!this.props.itemType) {
      return;
    }
    getItems(this.props.itemType).then((items) => {
      this.setState({ tableData: items, loaded: true });
    });
  }

  _addIdx(idx) {
    this.setState((prevState) => ({
      selectedRowsIdx: [...prevState.selectedRowsIdx, idx],
    }));
  }

  _delIdx(idx) {
    this.setState((prevState) => {
      const newSelectedRowsIdx = prevState.selectedRowsIdx.filter(
        (item) => item !== idx
      );
      return { selectedRowsIdx: newSelectedRowsIdx };
    });
  }

  handleClick = (e) => {
    let dataIndex = e.currentTarget.rowIndex - 1;
    let action = e.currentTarget.classList.contains("active")
      ? () => {
          e.currentTarget.classList.remove("active");
          this._delIdx(dataIndex);
        }
      : () => {
          e.currentTarget.classList.add("active");
          this._addIdx(dataIndex);
        };

    action();
  };

  addItemsToCart = () => {
    const itemsToAdd = [];
    this.state.selectedRowsIdx.forEach((ind) => {
      let dataToAdd = this.state.tableData[ind];
      dataToAdd["itemType"] = this.props.itemType;
      itemsToAdd.push(dataToAdd);
    });

    addPageCartItems(itemsToAdd);
  };

  render() {
    if (!this.state.loaded) {
      return <div />;
    }
    return (
      <div>
        <Button name="Add to cart" onClick={this.addItemsToCart}></Button>
        <CompItemCatalogue
          tableData={this.state.tableData}
          handleClick={this.handleClick}
        ></CompItemCatalogue>
      </div>
    );
  }
}

export default CompPageItemCatalogue;
