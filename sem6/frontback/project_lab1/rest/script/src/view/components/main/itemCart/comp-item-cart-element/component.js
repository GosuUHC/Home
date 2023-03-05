import template from "./template.js";

class CompItemCartElement extends HTMLElement {

    constructor() {
        super();
        this._selected = false;
        this._count = 1;
        this._tableElement = undefined;
        this._root = this.attachShadow({ mode: "closed" });
    }

    setTableElement(tableElement) {
        this._tableElement = tableElement;
        this._render();
    }

    getCheckState() {
        return this._selected;
    }

    getCount() {
        return this._count;
    }

    getItemIdType() {
        let itemid = this._tableElement.getField("id");
        let itemType = this._tableElement.getField("type");
        return { itemid, itemType }
    }

    getItemText() {
        return this._tableElement.getRemainingFields(["id", "price"], true);
    }

    getItemData() {
        return this._tableElement.getField("id");
    }

    getFinalPrice() {
        return this._count * Number(this._tableElement.getField("price"));
    }

    connectedCallback() {
        this._render();
        let inputs = this._root.querySelectorAll("input");

        inputs[0].addEventListener("click", () => {
            this._selected = !this._selected;
        });

        inputs[1].addEventListener("click", () => {
            this._count = inputs[1].value;
        });
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}

customElements.define("comp-item-cart-element", CompItemCartElement);
