import template from "./template.js";
import { TableElement } from "../../../../model/main/modelMain.js";
import { addOrder } from "../../../../model/main/orders/modelOrders.js";
import {
    getPageCartItemsList,
    deletePageCartItems,
} from "../../../../model/main/itemCart/modelItemCart.js";



class CompPageItemCart extends HTMLElement {

    constructor() {
        super();
        this._root = this.attachShadow({ mode: "closed" });
    }

    connectedCallback() {
        this._render();
        let delBtn = this._root.getElementById("delBtnID");
        let addToOrdsBtn = this._root.getElementById("addToOrdsBtn");

        delBtn.addEventListener("click", () => {
            this._delSelectedPageCartItems();
        });

        addToOrdsBtn.addEventListener("click", () => {
            this._collectNewOrder();
        });
    }

    async _fillItemCart() {
        await import("../../../components/main/itemCart/comp-item-cart-element/component.js");
        let itemsList = await getPageCartItemsList();

        itemsList.forEach(elem => {
            let item = new TableElement();
            item.set(elem);
            this._renderPageCartItem(item);
        });
    }

    _renderPageCartItem(item) { // TableElement instance
        let itemCartElement = document.createElement("comp-item-cart-element");
        itemCartElement.setTableElement(item);
        this._root.appendChild(itemCartElement);
    }

    _delSelectedPageCartItems() {
        let items = this._root.querySelectorAll("comp-item-cart-element");
        let i = 0;
        let indicesToDel = [];
        for (let item of items) {
            i++;
            if (!item.getCheckState()) {
                continue;
            }
            indicesToDel.push(i - 1);

        }
        deletePageCartItems(indicesToDel)
            .then(() => this._render());
    }

    _collectNewOrder() {
        let items = this._root.querySelectorAll("comp-item-cart-element");
        for (let item of items) {
            if (!item.getCheckState()) {
                continue;
            }

            let itemCount = item.getCount();
            let { itemid, itemType } = item.getItemIdType();

            addOrder(itemid, itemType, itemCount)
                .then((response) => alert(response));
        }
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
        this._fillItemCart();
    }
}

customElements.define("comp-page-item-cart", CompPageItemCart);
