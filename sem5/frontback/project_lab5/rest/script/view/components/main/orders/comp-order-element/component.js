import template from "./template.js";

class CompOrderElement extends HTMLElement {

    constructor() {
        super();
        this._selected = false;
        this._tableElementOrder = undefined;
        this._tableElementItem = undefined;
        this._root = this.attachShadow({ mode: "closed" });
    }

    setTableElement(tableElementOrder, tableElementItem) {
        this._tableElementOrder = tableElementOrder;
        this._tableElementItem = tableElementItem;
        this._render();
    }

    getCheckState() {
        return this._selected;
    }

    getOrderData() {
        return this._tableElementOrder.getRemainingFields(["id", "itemid", "userLogin", "item"]);
    }

    getItemData() {
        return this._tableElementItem.getRemainingFields(["id"], true);
    }

    getItemId() {
        return this._tableElementOrder.getField("itemid");
    }

    getOrderId() {
        return this._tableElementOrder.getField("id");
    }

    getPrice() {
        return this._tableElementOrder.getField("price");
    }

    connectedCallback() {
        this._render();
        let inputs = this._root.querySelectorAll("input");

        inputs[0].addEventListener("click", () => {
            this._selected = !this._selected;
        });
    }

    disconnectedCallback() {
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}

customElements.define("comp-order-element", CompOrderElement);
