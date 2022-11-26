import { getItems } from "../../../model/main/catalogueItems/modelCatalogue.js";
import { init as initMain, getTable } from "../pageMain.js";
import { saveItemToCart } from "../itemCart/pageItemCart.js";


let table;
let tableData;

/** init catalogue items */
function init() {
    let catalogueSelectValue = document.getElementById("itemSelect").value;
    initMain();
    document.getElementById("itemSelect").value = catalogueSelectValue;
    let main = document.querySelector("main");
    table = getTable();

    let goBackBtn = document.createElement("input");
    goBackBtn.id = "goBackBtn";
    goBackBtn.type = "button";
    goBackBtn.value = "Go back";
    goBackBtn.addEventListener("click", initMain);
    main.prepend(goBackBtn);

    let addToCartBtn = document.createElement("input");
    addToCartBtn.id = "addToCartBtn";
    addToCartBtn.type = "button";
    addToCartBtn.value = "Add to cart";
    addToCartBtn.addEventListener("click", saveItemToCart);
    main.append(addToCartBtn);

    //////////отдельно рендер и тп и тд ///////////
    getItems(catalogueSelectValue)
        .then(response => {
            tableData = response;
            fillTableCatalogue();
        });
}

function fillTableCatalogue() { // // / / / / ^^^

    let trHeader = document.createElement("tr");
    let headers = Object.keys(tableData[0]); // get keys

    for (let i = 0; i < headers.length; i++) {
        let th = document.createElement("th");
        th.textContent = headers[i];
        trHeader.appendChild(th);
    }

    table.appendChild(trHeader);

    for (let i = 0; i < tableData.length; i++) {
        let trItem = document.createElement("tr");
        let values = Object.values(tableData[i]);

        for (let j = 0; j < values.length; j++) {
            let tdItem = document.createElement("td");
            tdItem.textContent = values[j];
            trItem.appendChild(tdItem);
        }

        table.appendChild(trItem);

    }
    catalogueOnClick();
}

function catalogueOnClick() {
    let trs = table.childNodes; // <tr>

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


export { init, };
