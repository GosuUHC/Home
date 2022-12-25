import template from "./template";
import { TableElement } from "../../../../model/main/modelMain.js";
import {
    getAllOrders,
    deleteOrder,
} from "../../../../model/main/orders/modelOrders.js";

class CompPageOrders extends HTMLElement {

    constructor() {
        super();
        this._tableData = undefined;
        this._root = this.attachShadow({ mode: "closed" })
    }

    connectedCallback() {
        this._render();
        this._getData()
            .then(() => this._renderOrders())
            .then(() => this._init())
    }

    async _getData() {
        this._tableData = await getAllOrders();
    }

    _init() {
        let delBtn = this._root.getElementById("delBtnID");
        delBtn.addEventListener("click", () => this._deleteSelectedOrders());
    }

    _deleteSelectedOrders() {
        let orders = this._root.querySelectorAll("comp-order-element");

        for (let order of orders) {
            if (!order.getCheckState()) {
                continue;
            }

            let ordid = order.getOrderId();
            deleteOrder(ordid)
                .then(response => alert(response));
        }

        this._render();
        this._getData()
            .then(() => this._renderOrders());
    }

    async _renderOrders() {
        await import("../../../components/main/orders/comp-order-element/component.js");

        for (let i = 0; i < this._tableData.length; i++) {
            let order = new TableElement();
            let item = new TableElement();
            item.set(this._tableData[i]["item"]);
            order.set(this._tableData[i]);
            this._renderOrder(order, item);
        }
    }

    _renderOrder(order, item) { // TableElement instance
        let orderElement = document.createElement("comp-order-element");
        orderElement.setTableElement(order, item);
        this._root.appendChild(orderElement);
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}

customElements.define("comp-page-orders", CompPageOrders);



