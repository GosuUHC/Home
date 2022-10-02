

function getTableData() {
    var tableData = {}

    tables = document.querySelectorAll("table");

    // table
    for (var i = 0; i < tables.length; i++) {
        tableData[i] = [];
        var currTable = tables[i];
        var currRows = tables[i].querySelectorAll("tr");

        // tr
        for (var j = 0; j < currRows.length; j++) {
            tableData[i][j] = [];
            var currRow = currRows[j];
            var cells = currRow.querySelectorAll("td");

            // td
            for (var k = 0; k < cells.length; k++) {

                if (cells[k].firstChild.type) {
                    tableData[i][j][k] = cells[k];
                } else {
                    tableData[i][j][k] = cells[k].textContent;
                }


            }
        }
    }
    return tableData;
}

function addTableCheckBoxes() {

    document.querySelectorAll("tr").forEach((row, i) => {
        var input = document.createElement("input");
        input.setAttribute("type", "checkbox");
        if (i) {
            var cell = document.createElement("td");
            cell.appendChild(input);
            row.appendChild(cell);
        } else {
            var cell = document.createElement("th");
            cell.innerHTML = "Delete";
            row.appendChild(cell);
        }

    });
}

function sendToServ() {
    var tableData = getTableData();

    for (var i = 1; i < tableData[0].length; i++) {

        if (!tableData[0][i][5].firstChild.checked) {
            continue;
        }

        var sendData = "Surname=" + tableData[0][i][1] + "&Name=" + tableData[0][i][2]
            + "&MiddleName=" + tableData[0][i][3] + "&Count=" + tableData[0][i][4];

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "delete");
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(sendData);

    }
}

function delBtnAddListener() {
    var entBtn = document.getElementById("delBtn");
    entBtn.addEventListener("click", sendToServ);
}


