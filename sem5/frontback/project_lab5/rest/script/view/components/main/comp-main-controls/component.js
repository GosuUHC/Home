import template from "./template";

class CompMainControls extends HTMLElement {

    constructor() {
        super();
        this._logoutListener = undefined;
        this._catalogueSelectListener = undefined;
        this._itemCartListener = undefined;
        this._ordersListener = undefined;
        this._selectValue = undefined;
        this._root = this.attachShadow({ mode: "closed" });
    }

    connectedCallback() {
        this._render();
    }

    getSelectValue() {
        return this._selectValue;
    }

    setLogoutListener(logoutListener) {
        this._logoutListener = logoutListener;
        let logoutBtn = this._root.getElementById("logout");
        logoutBtn.addEventListener("click", () => {
            this._logoutListener();
        });
    }

    setCatalogueSelectListener(catalogueSelectListener) {
        this._catalogueSelectListener = catalogueSelectListener;
        let catalogueSelect = this._root.getElementById("itemSelect");
        catalogueSelect.addEventListener("change", () => {
            this._selectValue = catalogueSelect.value;
            this._catalogueSelectListener();
        });
    }

    setItemCartListener(itemCartListener) {
        this._itemCartListener = itemCartListener;
        let itemCartBtn = this._root.getElementById("itemCartButtonID");
        itemCartBtn.addEventListener("click", () => {
            this._itemCartListener();
        });
    }

    setOrdersListener(ordersListener) {
        this._ordersListener = ordersListener;
        let ordersBtn = this._root.getElementById("ordersButtonID");
        ordersBtn.addEventListener("click", () => {
            this._ordersListener();
        });
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}
customElements.define("comp-main-controls", CompMainControls);


/*
import { logout } from "../auth/pageAuth.js";
import { init as catalogueInit } from "./catalogueItems/pageCatalogue.js";
import { init as ordersInit } from "./orders/pageOrders.js";
import { init as itemCartInit } from "./itemCart/pageItemCart.js";


let root;
let table;

/** Init Main page */
/*
function init() {
    root = document.getElementById("root");
    root.innerHTML = innerHTMLMain;
    table = document.getElementsByClassName("pageCart")[0];

    //logout btn
    let logoutBtn = document.getElementById("logoutBtn");
    logoutBtn.addEventListener("click", logout);

    //catalogue select
    let catalogueSelect = document.getElementById("itemSelect");
    catalogueSelect.addEventListener("change", catalogueInit);

    // изменять локальное состояние
    // и потом вызывать рендер

    //orders btn
    let ordersBtn = document.getElementsByClassName("ordersButton").item(0);
    ordersBtn.addEventListener("click", ordersInit);

    // item cart btn
    let itemCartBtn = document.getElementsByClassName("itemCartButton").item(0);
    itemCartBtn.addEventListener("click", itemCartInit);
}

function resetTable() {
    document.getElementsByClassName("pageCart").item(0).innerHTML = "";
}

function getTable() {
    return table;
}

function initDelBtn(eventFunc) {
    let deleteDiv = document.createElement("div");
    deleteDiv.className = "delBtnDiv";
    deleteDiv.textContent = "Delete selected";
    deleteDiv.addEventListener("click", eventFunc);
    document.querySelector("main").insertBefore(deleteDiv, table);
}


export {
    init,
    resetTable,
    getTable,
    initDelBtn,
};
*/