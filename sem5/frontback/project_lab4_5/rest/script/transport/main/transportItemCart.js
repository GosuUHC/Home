async function getPageCartItemsList() {
    await 1;
    let itemsList = localStorage.getItem("itemsList");

    if (itemsList == undefined || itemsList == null) {
        itemsList = [];
    }
    else {
        itemsList = JSON.parse(itemsList);
    }

    return itemsList;
}

async function addPageCartItem(keys, values) {
    await 1;
    let item = {};
    for (let i = 0; i < keys.length; i++) {
        item[keys[i]] = values[i];
    }
    addItem(item);
}

async function addItem(item) {
    let itemsList = await getPageCartItemsList();
    itemsList.push(item);
    localStorage.setItem("itemsList", JSON.stringify(itemsList));
}

async function delPageCartItem(indicesToDel) {
    let itemsList = await getPageCartItemsList();
    for (let ind of indicesToDel.reverse()) {
        itemsList.splice(ind, 1);
    }
    localStorage.setItem("itemsList", JSON.stringify(itemsList));
}

export {
    getPageCartItemsList,
    addPageCartItem,
    delPageCartItem
};