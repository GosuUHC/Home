import {
    getPageCartItemsList as getPageCartItemsListTransport,
    addPageCartItem as addPageCartItemTransport,
    delPageCartItem as delPageCartItemTransport,
} from "../../../transport/main/transportItemCart.js";


function getPageCartItemsList() {
    return getPageCartItemsListTransport();
}

function addPageCartItem(keys, values) {
    return addPageCartItemTransport(keys, values);
}

function deletePageCartItems(indicesToDel) {
    return delPageCartItemTransport(indicesToDel);
}

function filterItemData(itemText) {
    let itemid = itemText.match(/Item id: [\d]*/ig)[0];
    itemid = itemid.match(/(0|[1-9]\d*)$/)[0];

    let itemType = itemText.match(/type: \w*/ig)[0];
    itemType = itemType.match(/: \w*/);
    itemType = itemType.toString().replace(/(: )/gm, "");

    return { itemid, itemType };
}

export {
    getPageCartItemsList,
    addPageCartItem,
    deletePageCartItems,
    filterItemData,
};
