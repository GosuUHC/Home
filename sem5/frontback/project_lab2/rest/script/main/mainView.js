var innerHTMLMain = `
    <main id="loginMain">
        <div id="logout">
            <input type="button" id="logoutBtn" value="logout">
        </div>
        <div id="catalogueDiv">
            <label for="itemSelect">Catalogue:</label>

            <select id="itemSelect">
                <option value="">Choose an item</option>
                <option value="cpu">CPU</option>
                <option value="gpu">GPU</option>
                <option value="ram">RAM</option>
                <option value="motherboard">Motherboard</option>
            </select>
        </div>
        <div id="mainTableDiv">
            <table id="mainTable">
                
            </table>
        </div>
    </main>
`;

function resetTable() {
    var table = document.getElementById("mainTable");
    table.innerHTML = "";
}

function fillTable(tableData = [{}]) {
    var table = document.getElementById("mainTable");

    for (var i = 0; i < tableData.length; i++) {
        var trHeader = document.createElement("tr");

        var th1 = document.createElement("th");
        var th2 = document.createElement("th");

        th1.textContent = "№ " + (i + 1);

        input = document.createElement("input");
        input.setAttribute("type", "checkbox");
        th2.appendChild(input);

        trHeader.appendChild(th1);
        trHeader.appendChild(th2);


        var trData = document.createElement("tr");

        var tdOrder = document.createElement("td");

        item = tableData[i].item;
        delete tableData[i].item;

        tdOrder.textContent = JSON.stringify(tableData[i], null, 4);

        var trData2 = document.createElement("tr");
        var tdItem = document.createElement("td");
        tdItem.textContent = JSON.stringify(item, null, 4);

        trData2.appendChild(tdItem);

        trData.appendChild(tdOrder);
        table.appendChild(trHeader);
        table.appendChild(trData);
        table.appendChild(trData2);

    }
}

function fillTableCatalogue(tableItems = [{}]) {
    var table = document.getElementById("mainTable");
    var trHeader = document.createElement("tr");

    var headers = Object.keys(tableItems[0]); // get keys

    console.log(headers);
    console.log(tableItems);

    for (var i = 0; i < headers.length; i++) {
        var th = document.createElement("th");
        th.textContent = headers[i];
        trHeader.appendChild(th);
    }

    table.appendChild(trHeader);

    for (var i = 0; i < tableItems.length; i++) {
        var trItem = document.createElement("tr");
        values = Object.values(tableItems[i]);


        for (var j = 0; j < values.length; j++) {
            var tdItem = document.createElement("td");
            tdItem.textContent = values[j];
            trItem.appendChild(tdItem);
        }

        table.appendChild(trItem);

    }
    highlightTable();

}

function highlightTable() {
    var trs = document.querySelectorAll("tr");

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

