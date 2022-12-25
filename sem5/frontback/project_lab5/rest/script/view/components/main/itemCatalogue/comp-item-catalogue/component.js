import template from "./template.js";
import { addPageCartItem } from "../../../../../model/main/itemCart/modelItemCart.js"

class CompItemCatalogue extends HTMLElement {

    constructor() {
        super();
        this._tableData = undefined;
        this._table = undefined;
        this._itemType = undefined;
        this._root = this.attachShadow({ mode: "closed" });
    }

    setTableData(data) {
        this._tableData = data;
        if (this._tableData) {
            this._table = this._root.getElementById("catalogueTableData");
            this._renderTableData();
        }
    }

    setItemType(itemType) {
        this._itemType = itemType;
    }

    connectedCallback() {
        this._render();
    }

    _renderTableData() {

        let trHeader = document.createElement("tr");
        let headers = Object.keys(this._tableData[0]); // get keys

        for (let i = 0; i < headers.length; i++) {
            let th = document.createElement("th");
            th.textContent = headers[i];
            trHeader.appendChild(th);
        }

        this._table.appendChild(trHeader);

        for (let i = 0; i < this._tableData.length; i++) {
            let trItem = document.createElement("tr");
            let values = Object.values(this._tableData[i]);

            for (let j = 0; j < values.length; j++) {
                let tdItem = document.createElement("td");
                tdItem.textContent = values[j];
                trItem.appendChild(tdItem);
            }

            this._table.appendChild(trItem);

        }
        this._catalogueOnClick();
    }

    _catalogueOnClick() {
        let trs = this._table.childNodes; // <tr>

        trs.forEach(element => {
            element.addEventListener("click", () => {
                trs.forEach(elem => {
                    if (elem.className == "active") {
                        elem.className = "none";
                    }
                });
                if (element.className == "active") {
                    element.className = "none";
                    return;
                }
                element.className = "active";
            });
        });
    }

    addToCart() {
        let tableTh = this._root.querySelectorAll("th");
        let tableTr = this._root.querySelectorAll("tr");
        let tableActiveTr = [];
        tableTr.forEach((elem, idx) => {
            if (elem.className === "active") {
                tableActiveTr.push(elem);
            }
        });
        let headers = [];
        let values = [];
        tableTh.forEach(element => {
            headers.push(element.textContent);
        });
    
        for (let i = 0; i < tableActiveTr.length; i++) {
            let cells = tableActiveTr[i].cells;
            for (let elem of cells) {
                values.push(elem.textContent)
            }
        }

        let option = this._itemType;
        headers.push("type");
        values.push(option);
        addPageCartItem(headers, values);
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}

customElements.define("comp-item-catalogue", CompItemCatalogue);
