import {
    getAllOrders,
    addOrder,
    deleteOrder,
} from "../../../model/main/orders/modelOrders.js";
import {
    init as initMain,
    getTable,
    initDelBtn,
} from "../pageMain.js";
import { TableElement } from "../../../model/main/modelMain.js";


let table;
let tableData;

/** init orders */
function init() {
    initMain()
    table = getTable();
    getAllOrders().then(response => {
        tableData = response;
        initDelBtn(deleteSelectedOrders);
        fillOrders();
    });
}

function renderOrder(order, item) { // TableElement instance
    let innerHTMLItem = `
        <div class="mainTableDivItem">
            <div class="itemCheckBoxDiv">
                <input class="itemCheckBox" type="checkbox">
            </div>
            
            <div class="itemImage">

            </div>

            <div class="itemContent">
                <div class="itemDescription">

                    <div class="itemDescriptionText">
                        ${order.getRemainingFields(["id", "itemid", "userLogin", "item"])}
                        <br>
                        Item: ${item.getRemainingFields(["id"], true)}
                    </div>
                    <div class="itemDescriptionData">
                        Item id: ${order.getField("itemid")}
                        Order id: ${order.getField("id")}
                    </div>

                </div>

                <div class="itemPrice">
                    <span> ${order.getField("price")} P</span>
                </div>  

            </div>

        </div>
        `;
    table.innerHTML += innerHTMLItem;
}

function fillOrders() {
    let addToCart = document.getElementById("addOrderBtn");
    if (addToCart) {
        addToCart.remove();
    }

    for (let i = 0; i < tableData.length; i++) {
        let order = new TableElement();
        let item = new TableElement();
        item.set(tableData[i]["item"]);
        order.set(tableData[i]);
        renderOrder(order, item);
    }
}

function deleteSelectedOrders() {
    let checkboxes = document.getElementsByClassName("itemCheckBox");

    for (let checkbox of checkboxes) {
        if (!checkbox.checked) {
            continue;
        }

        let ordText = checkbox.parentElement.parentElement.outerText;
        deleteOrder(ordText)
            .then(response => alert(response));
    }

    init();
}

function addNewOrders(itemid, itemType, itemCount) {
    addOrder(itemid, itemType, itemCount)
        .then(response => alert(response));
}

export {
    init,
    addNewOrders,
};
