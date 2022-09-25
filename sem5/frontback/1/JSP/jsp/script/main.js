
function testing() {
    var data = {}

    tables = document.querySelectorAll("table");

    // table
    for (var i = 0; i < tables.length; i++) {
        data[i] = [];
        var currTable = tables[i];
        var currRows = tables[i].querySelectorAll("tr");

        // tr
        for (var j = 0; j < currRows.length; j++) {
            data[i][j] = [];
            var currRow = currRows[j];
            var cells = currRow.querySelectorAll("td");

            // td
            for (var k = 0; k < cells.length; k++) {
                data[i][j][k] = cells[k].textContent;
            }
        }
    }

    var index = document.getElementById("inpDel").value;

    var inpSurname = document.getElementById("inSurnameHidD");
    var inpName = document.getElementById("inNameHidD");
    var inpMiddleName = document.getElementById("inMiddleNameHidD");
    var inpCount = document.getElementById("inCountHidD");

    inpSurname.value = data[0][index][1];
    inpName.value = data[0][index][2];
    inpMiddleName.value = data[0][index][3];
    inpCount.value = data[0][index][4];

    return true;
}