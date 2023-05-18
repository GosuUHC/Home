class CardElement {
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

  getRemainingFields(excludedFields = [], nameAndMnfct = false) {
    let mainFields = [];
    if (nameAndMnfct) {
      excludedFields.push("manufacturer");
      excludedFields.push("name");

      mainFields.push(this.data["manufacturer"] + " " + this.data["name"]);
    }
    for (let key in this.data) {
      if (excludedFields.includes(key)) {
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

export { CardElement };
