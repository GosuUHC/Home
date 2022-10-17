
function initMain() {

    // xhr send login and token to check authorization
    myXmlRequest("POST", "api/auth", getLoginData(), checkToken);
    root.innerHTML = innerHTMLMain;

    var logoutBtn = document.getElementById("logoutBtn");
    logoutBtn.addEventListener("click", initLogin);

    var deleteDiv = document.createElement("div");
    deleteDiv.className = "delBtnDiv";
    deleteDiv.textContent = "Delete selected";
    deleteDiv.addEventListener("click", deleteSelectedOrders);
    document.querySelector("main").append(deleteDiv);

    var catalogueSelect = document.getElementById("itemSelect");
    catalogueSelect.addEventListener("change", function () {
        resetTable();
        loadOption(catalogueSelect)
        initItems();
    });


}

function initItems() {
    if (document.getElementById("goBackBtn")) {
        return;
    }

    if (document.getElementsByClassName("delBtnDiv")) {
        document.getElementsByClassName("delBtnDiv").item(0).textContent = "";
    }

    var main = document.querySelector("main");

    // Go back button
    var goBackBtn = document.createElement("input");
    goBackBtn.id = "goBackBtn";
    goBackBtn.type = "button";
    goBackBtn.value = "Go back";
    goBackBtn.addEventListener("click", initMain);
    main.prepend(goBackBtn);

    // Add order button
    var addOrderBtn = document.createElement("input");
    addOrderBtn.id = "addOrderBtn";
    addOrderBtn.type = "button";
    addOrderBtn.value = "Add new order";
    addOrderBtn.addEventListener("click", addOrder)
    main.append(addOrderBtn);
}

function loadOption(catalogueSelect) {
    var value = catalogueSelect.value;
    var itemType = {
        "type": value
    }
    myXmlRequest("POST", "api/items", itemType, fillTableCatalogue);

}


function checkToken(response) {

    if (response !== localStorage.getItem("token")) {
        console.log("NO MATCH");
        return;
    }

    console.log("TOKENS MATCHED");
    console.log(response);
    console.log(localStorage.getItem("token"));
    myXmlRequest("GET", "api/orders", {}, fillTable);

}

function addOrder() {
    var tableTh = document.querySelectorAll("th");
    var tableActiveTr = document.getElementsByClassName("active");
    var headers = [];
    var idvalues = [];
    tableTh.forEach(function (element) {
        headers.push(element.textContent);
    });
    var idIndex = headers.indexOf("id");

    for (var i = 0; i < tableActiveTr.length; i++) {
        var cells = tableActiveTr[i].cells;

        idvalues.push(cells.item(idIndex).textContent);

    }

    var option = document.getElementById("itemSelect").value;

    var count = 1;
    console.log(idvalues);

    idvalues.forEach(function (element) {
        var item = {
            "id": element,
            "type": option,
            "count": count
        }
        myXmlRequest("POST", "api/orders", item, alert);
    });

}

function deleteSelectedOrders() {
    var rows = document.getElementsByTagName("tr");
    console.log("DELLLLLL");
    
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].cells.length != 2){
            continue;
        }
        var checkbox = rows[i].querySelectorAll("th");
        var checked = checkbox.item(1).childNodes.item(0).checked;
        if (!checked) {
            continue;
        }
        var item = JSON.parse(rows[i + 1].textContent);
        var itemid = {
            "id": item.id
        }

        myXmlRequest("PUT", "api/orders", itemid, alert);
    }

    initMain();
}