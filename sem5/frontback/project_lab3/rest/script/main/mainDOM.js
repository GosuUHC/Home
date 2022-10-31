var pageMain = (function () {

    var rootDiv;
    var innerHTMLMain = `
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

    var table;
    var tableData;

    function initMain() {
        rootDiv = document.getElementById("root");
        rootDiv.innerHTML = innerHTMLMain;

        table = document.getElementsByClassName("pageCart").item(0);

        var logoutBtn = document.getElementById("logoutBtn");
        logoutBtn.addEventListener("click", pageAuth.initLogin); //////////

        var catalogueSelect = document.getElementById("itemSelect");
        catalogueSelect.addEventListener("change", function () {
            resetTable();
            initCatalogue();
        });

        var ordersBtn = document.getElementsByClassName("ordersButton").item(0);
        ordersBtn.addEventListener("click", function () {
            modelMain.getOrders(ordersDataCallback);
        });

        var itemCartBtn = document.getElementsByClassName("itemCartButton").item(0);
        itemCartBtn.addEventListener("click", loadItemCart);

    }

    function initCatalogue() {
        var main = document.querySelector("main");

        // Go back button
        if (!document.getElementById("goBackBtn")) {
            var goBackBtn = document.createElement("input");
            goBackBtn.id = "goBackBtn";
            goBackBtn.type = "button";
            goBackBtn.value = "Go back";
            goBackBtn.addEventListener("click", initMain);
            main.prepend(goBackBtn);
        }

        var delBtnDiv = document.getElementsByClassName("delBtnDiv").item(0);
        if (delBtnDiv) {
            delBtnDiv.remove();
        }

        // Add order button
        if (!document.getElementById("addOrderBtn")) {
            var addOrderBtn = document.createElement("input");
            addOrderBtn.id = "addOrderBtn";
            addOrderBtn.type = "button";
            addOrderBtn.value = "Add to cart";
            addOrderBtn.addEventListener("click", saveItemToCart);
            main.append(addOrderBtn);
        }

        var catalogueSelectValue = document.getElementById("itemSelect").value;
        modelMain.getItems(catalogueSelectValue, catalogueTableDataCallback);
    }

    function resetTable() {
        table.innerHTML = "";
    }

    function saveItemToCart() {
        var tableTh = document.querySelectorAll("th");
        var tableActiveTr = document.getElementsByClassName("active");
        var headers = [];
        var values = [];
        tableTh.forEach(function (element) {
            headers.push(element.textContent);
        });

        for (var i = 0; i < tableActiveTr.length; i++) {
            var cells = tableActiveTr[i].cells;
            for (var elem of cells) {
                values.push(elem.textContent)
            }
        }

        var item = {};
        for (var x = 0; x < headers.length; x++) {
            item[headers[x]] = values[x];
        }
        var option = document.getElementById("itemSelect").value;
        item["type"] = option;


        var itemsList = getPageCartItemsList();
        itemsList.push(item);
        localStorage.setItem("itemsList", JSON.stringify(itemsList));

    }

    function getPageCartItemsList() { // returns array
        var itemsList = localStorage.getItem("itemsList");
        if (itemsList == undefined || itemsList == null) {
            itemsList = [];
        }
        else {
            itemsList = JSON.parse(itemsList);
        }
        return itemsList;
    }

    // class TableElement (Begin)
    function TableElement() {
        this.data = {};

        TableElement.prototype.set = function (tableItem) {
            this.data = tableItem;
        }

        TableElement.prototype.setField = function (fieldName, fieldData) {
            this.data[fieldName] = fieldData;
        }

        TableElement.prototype.get = function () {
            return this.data;
        }

        TableElement.prototype.getRemainingFields = function (wrongFields = [], nameAndMnfct = false) {
            var mainFields = [];
            if (nameAndMnfct) {
                wrongFields.push("manufacturer");
                wrongFields.push("name");

                mainFields.push(this.data["manufacturer"] + " " + this.data["name"]);
            }
            for (var key in this.data) {
                if (wrongFields.includes(key)) {
                    continue;
                }
                mainFields.push(" " + key + ": " + this.data[key]);

            }
            return mainFields;
        }

        TableElement.prototype.getField = function (fieldName) {
            return this.data[fieldName];
        }
    }
    // class TableElement (End)

    function renderPageCartItem(item) { // TableElement instance

        var itemCount = 1;

        var innerHTMLItem = `
        <div class="mainTableDivItem">
            <div class="itemCheckBoxDiv">
                <input class="itemCheckBox" type="checkbox">
            </div>
            
            <div class="itemImage">

            </div>

            <div class="itemContent">
                <div class="itemDescription">

                    <div class="itemDescriptionText">
                        ${item.getRemainingFields(["id", "price", "position"], true)}
                    </div>
                    <div class="itemDescriptionData">
                        Item id: ${item.getField("id")}
                    </div>

                </div>
                <div class="itemPrice">
                    <span> ${itemCount * item.getField("price")} P</span>
                </div>
                
            </div>

        </div>
        `;
        table.innerHTML += innerHTMLItem;

    }
    function renderOrder(order) { // TableElement instance

        var innerHTMLItem = `
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

    function ordersDataCallback(response) {
        tableData = response;
        resetTable();
        fillOrders();
    }

    function initDelBtn(eventFunc) {
        var deleteDiv = document.getElementsByClassName("delBtnDiv").item(0);
        console.log(deleteDiv);
        if (deleteDiv != null && deleteDiv != undefined) {
            deleteDiv.remove();
        }

        deleteDiv = document.createElement("div");
        deleteDiv.className = "delBtnDiv";
        deleteDiv.textContent = "Delete selected";
        deleteDiv.addEventListener("click", eventFunc);
        document.querySelector("main").insertBefore(deleteDiv, table);
    }

    function fillOrders() {
        var addToCart = document.getElementById("addOrderBtn");
        if (addToCart) {
            addToCart.remove();
        }

        initDelBtn(deleteSelectedOrders);

        for (var i = 0; i < tableData.length; i++) {
            var order = new TableElement();
            delete tableData[i].item;
            order.set(tableData[i]);
            renderOrder(order);
        }
    }

    function loadItemCart() {
        var addToCart = document.getElementById("addOrderBtn");
        if (addToCart) {
            addToCart.remove();
        }
        resetTable();
        initDelBtn(deleteSelectedPageCartItems);
        var itemsList = getPageCartItemsList();
        itemsList.forEach(function (elem) {
            var tableElement = new TableElement();
            tableElement.set(elem);
            renderPageCartItem(tableElement);
        });
        var addOrderBtn = document.createElement("input");
        addOrderBtn.id = "addOrderBtn";
        addOrderBtn.type = "button";
        addOrderBtn.value = "Add orders";
        addOrderBtn.addEventListener("click", collectNewOrder);
        table.appendChild(addOrderBtn);

    }

    function catalogueTableDataCallback(response) {
        tableData = response;
        resetTable();
        fillTableCatalogue();
    }

    function fillTableCatalogue() {

        var trHeader = document.createElement("tr");
        var headers = Object.keys(tableData[0]); // get keys

        for (var i = 0; i < headers.length; i++) {
            var th = document.createElement("th");
            th.textContent = headers[i];
            trHeader.appendChild(th);
        }

        table.appendChild(trHeader);

        for (var i = 0; i < tableData.length; i++) {
            var trItem = document.createElement("tr");
            values = Object.values(tableData[i]);

            for (var j = 0; j < values.length; j++) {
                var tdItem = document.createElement("td");
                tdItem.textContent = values[j];
                trItem.appendChild(tdItem);
            }

            table.appendChild(trItem);

        }
        catalogueOnClick();
    }

    function catalogueOnClick() {
        trs = table.childNodes; // <tr>

        trs.forEach(function (element) {
            element.addEventListener("click", function () {
                trs.forEach(function (elem) {
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

    function deleteSelectedPageCartItems() {
        var checkboxes = document.getElementsByClassName("itemCheckBox");
        var i = 0;
        var indexesToDelete = []
        for (var checkbox of checkboxes) {
            i++;
            if (!checkbox.checked) {
                continue;
            }
            indexesToDelete.push(i - 1);

        }

        var itemsList = getPageCartItemsList();
        for (var ind of indexesToDelete.reverse()) {
            itemsList.splice(ind, 1);
        }

        localStorage.setItem("itemsList", JSON.stringify(itemsList));

        loadItemCart(); // render with updated listf
    }

    function collectNewOrder() {
        var checkboxes = document.getElementsByClassName("itemCheckBox");
        for (var checkbox of checkboxes) {
            if (!checkbox.checked) {
                continue;
            }
            console.log(checkbox.parentElement.parentElement.outerText.replace(/(\r\n|\n|\r)/gm, " "));

            var itemData = checkbox.parentElement.parentElement.outerText.match(/Item id: [\d]*/ig)[0];
            itemid = itemData.match(/(0|[1-9]\d*)$/)[0];

            var itemType = checkbox.parentElement.parentElement.outerText.match(/type: \w*/ig)[0];
            itemType = itemType.match(/: \w*/);
            itemType = itemType.toString().replace(/(: )/gm, "");

            var itemCount = 1; // = input.value .....

            modelMain.addOrder(itemid, itemType, itemCount);
        }

    }

    function deleteSelectedOrders() {
        var checkboxes = document.getElementsByClassName("itemCheckBox");

        for (var checkbox of checkboxes) {
            if (!checkbox.checked) {
                continue;
            }

            var ordid = checkbox.parentElement.parentElement.outerText.match(/Order id: [\d]*/ig)[0];
            ordid = ordid.match(/(0|[1-9]\d*)$/)[0];

            var del = {
                "id": ordid
            }
            modelMain.deleteOrder(del);
        }

        modelMain.getOrders(ordersDataCallback);

    }

    return {
        initMain: initMain,
        initItems: initCatalogue,
        collectNewOrder: collectNewOrder,
        fillTableCatalogue: fillTableCatalogue,

    };
}
)();
