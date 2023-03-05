import "../../../components/main/itemCatalogue/comp-item-catalogue/component.js";
import { getItems } from "../../../../model/main/catalogueItems/modelCatalogue.js";
import template from "./template.js";

class CompPageItemCatalogue extends HTMLElement {

    constructor() {
        super();
        this._itemType = undefined;
        this._root = this.attachShadow({ mode: "closed" });
    }

    connectedCallback() { 
        this._render();
        let catalogue = document.createElement("comp-item-catalogue");
        getItems(this._itemType)
            .then((data) => {
                this._root.appendChild(catalogue);
                catalogue.setItemType(this._itemType);
                catalogue.setTableData(data);
            })
            .then(() => {
                let addToCartBtn = this._root.getElementById("addToCartBtn");
                addToCartBtn.addEventListener("click", () => {
                    catalogue.addToCart();
                });
            });
    }

    setItemType(type) {
        this._itemType = type;
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}

customElements.define("comp-page-item-catalogue", CompPageItemCatalogue);
