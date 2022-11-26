class TableElement {
    constructor() {
        this.data = {};
    }

    set(tableItem) {
        this.data = tableItem;
    }

    get() {
        return this.data;
    }

    setField(fieldName, fieldData) {
        this.data[fieldName] = fieldData;
    }

    getRemainingFields(wrongFields = [], nameAndMnfct = false) {
        let mainFields = [];
        if (nameAndMnfct) {
            wrongFields.push("manufacturer");
            wrongFields.push("name");

            mainFields.push(this.data["manufacturer"] + " " + this.data["name"]);
        }
        for (let key in this.data) {
            if (wrongFields.includes(key)) {
                continue;
            }
            mainFields.push(" " + key + ": " + this.data[key]);
        }
        return mainFields;
    }

    getField(fieldName) {
        return this.data[fieldName];
    }
}


export { TableElement };
