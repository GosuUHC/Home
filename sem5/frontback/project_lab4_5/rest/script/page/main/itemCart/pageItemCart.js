import {
    init as initMain,
    getTable,
    initDelBtn,
} from "../pageMain.js";
import {
    getPageCartItemsList,
    addPageCartItem,
    deletePageCartItems,
    filterItemData,
    validateItemCount,
} from "../../../model/main/itemCart/modelItemCart.js";
import { TableElement } from "../../../model/main/modelMain.js";
import { addNewOrders } from "../orders/pageOrders.js";


let table;

/** Init page cart items */
function init() {
    initMain();
    table = getTable();

    initDelBtn(delSelectedPageCartItems);
    fillItemCart();

    let addOrderBtn = document.createElement("input");
    addOrderBtn.id = "addOrderBtn";
    addOrderBtn.type = "button";
    addOrderBtn.value = "Add to orders";
    addOrderBtn.addEventListener("click", collectNewOrder);
    table.appendChild(addOrderBtn);

    let itemCounts = document.getElementsByClassName("itemCountValue");

}

function fillItemCart() {
    getPageCartItemsList().then(itemsList => {
        itemsList.forEach(elem => {
            let item = new TableElement();
            item.set(elem);
            renderPageCartItem(item);
        });
    });
}

function saveItemToCart() {
    let tableTh = document.querySelectorAll("th");
    let tableActiveTr = document.getElementsByClassName("active");
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

    let option = document.getElementById("itemSelect").value;
    headers.push("type");
    values.push(option);
    addPageCartItem(headers, values);
}

function renderPageCartItem(item) { // TableElement instance

    let itemCount = 1;

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
                        ${item.getRemainingFields(["id", "price"], true)}
                    </div>
                    <div class="itemDescriptionData">
                        Item id: ${item.getField("id")}
                    </div>

                </div>
                
                <div class="itemPrice">
                    <span> ${itemCount * item.getField("price")} P</span>
                </div>

                <article class="itemActions">
                    <div class="itemCountAction">
                        <input class="itemCountValue" type="number" min="1" max="10" value="1">
                    </div>
                </article>
                
            </div>

        </div>
        `;
    table.insertAdjacentHTML("beforeend", innerHTMLItem);
}

function delSelectedPageCartItems() {
    let checkboxes = document.getElementsByClassName("itemCheckBox");
    let i = 0;
    let indicesToDel = [];
    for (let checkbox of checkboxes) {
        i++;
        if (!checkbox.checked) {
            continue;
        }
        indicesToDel.push(i - 1);

    }
    deletePageCartItems(indicesToDel)
        .then(() => init());
}

function collectNewOrder() {
    let checkboxes = document.getElementsByClassName("itemCheckBox");
    for (let checkbox of checkboxes) {
        if (!checkbox.checked) {
            continue;
        }

        let itemText = checkbox.parentElement.parentElement.outerText;
        let itemCount = checkbox.parentElement.parentElement.children[2].children[2].children[0].firstElementChild.value;
        let { itemid, itemType } = filterItemData(itemText);

        addNewOrders(itemid, itemType, itemCount);
    }
}

export {
    saveItemToCart,
    init,
};
