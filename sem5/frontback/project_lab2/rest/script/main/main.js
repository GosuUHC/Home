
function initMain() {

    // xhr send login and token to check authorization
    myXmlRequest("POST", "api/auth", getLoginData(), checkToken);
    root.innerHTML = innerHTMLMain;

    var logoutBtn = document.getElementById("logoutBtn");
    logoutBtn.addEventListener("click", initLogin);

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

    var main = document.querySelector("main");

    var goBackBtn = document.createElement("input");
    goBackBtn.id = "goBackBtn";
    goBackBtn.type = "button";
    goBackBtn.value = "Go back";
    goBackBtn.addEventListener("click", initMain);
    main.prepend(goBackBtn);

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
    var values = [];
    tableTh.forEach(function (element) {
        headers.push(element.textContent);
    });

    console.log(tableActiveTr);

}
