import {
  getPageCartItemsList as getPageCartItemsListTransport,
  addPageCartItem as addPageCartItemTransport,
  delPageCartItem as delPageCartItemTransport,
} from "transport/main/transportItemCart.js";

function getPageCartItemsList() {
  return getPageCartItemsListTransport();
}

function addPageCartItems(itemsToAdd) {
  return addPageCartItemTransport(itemsToAdd);
}

function deletePageCartItems(indicesToDel) {
  return delPageCartItemTransport(indicesToDel);
}

function filterItemData(itemText) {
  let itemid = itemText.match(/Item id: [\d]*/gi)[0];
  itemid = itemid.match(/(0|[1-9]\d*)$/)[0];

  let itemType = itemText.match(/type: \w*/gi)[0];
  itemType = itemType.match(/: \w*/);
  itemType = itemType.toString().replace(/(: )/gm, "");

  return { itemid, itemType };
}

function validateItemCount(count) {
  try {
    let num = parseInt(count);
    if (num !== null && num !== undefined && num > 0 && num < 11) {
      return true;
    }
  } catch (err) {
    return false;
  }
}

export {
  getPageCartItemsList,
  addPageCartItems,
  deletePageCartItems,
  filterItemData,
  validateItemCount,
};
