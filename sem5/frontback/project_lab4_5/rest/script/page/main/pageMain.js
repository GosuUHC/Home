import { logout } from "../auth/pageAuth.js";
import { init as catalogueInit } from "./catalogueItems/pageCatalogue.js";
import { init as ordersInit } from "./orders/pageOrders.js";
import { init as itemCartInit } from "./itemCart/pageItemCart.js";


let root;
let table;
let innerHTMLMain = `
        <main id="main">
            <div id="logout">
                <input type="button" id="logoutBtn" value="logout">
            </div>
            <div id="catalogueDiv" class="catalogue">
                <select id="itemSelect">
                    <option value="">Catalogue</option>
                    <option value="cpu">CPU</option>
                    <option value="gpu">GPU</option>
                    <option value="ram">RAM</option>
                    <option value="motherboard">Motherboard</option>
                </select>
                <div class="catalogueActions">
                    <input type="button" class="itemCartButton" value="Item cart">
                    <input type="button" class="ordersButton" value="Orders">
                </div>
            </div>
            <div class="pageCart">

            </div>
        </main>
    `;

/** Init Main page */
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
