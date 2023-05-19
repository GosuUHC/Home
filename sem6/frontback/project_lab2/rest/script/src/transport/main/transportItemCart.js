import { ItemElement } from "model/dto/ItemElement";

function convert(response) {
  const itemCartData = response.map((elem) => {
    const item = Object.assign({}, ItemElement);
    item.id = elem.id;
    item.img = elem.img;
    item.manufacturer = elem.manufacturer;
    item.name = elem.name;
    item.price = elem.price;
    item.itemType = elem.itemType;

    item.other = {};
    for (const key in elem) {
      if (!item.hasOwnProperty(key)) {
        item.other[key] = elem[key];
      }
    }

    return item;
  });
  return itemCartData;
}

async function getPageCartItemsList() {
  await 1;
  let itemsList = localStorage.getItem("itemsList");

  if (itemsList === undefined || itemsList === null) {
    itemsList = [];
  } else {
    itemsList = JSON.parse(itemsList);
  }

  return convert(itemsList);
}

async function addPageCartItem(itemsToAdd) {
  await 1;
  for (const elem of itemsToAdd) {
    await addItem(elem);
  }
}

async function addItem(item) {
  const itemsList = await getPageCartItemsList();
  itemsList.push(item);
  localStorage.setItem("itemsList", JSON.stringify(itemsList));
}

async function delPageCartItem(indicesToDel) {
  const itemsList = await getPageCartItemsList();
  for (let ind of indicesToDel.reverse()) {
    itemsList.splice(ind, 1);
  }
  localStorage.setItem("itemsList", JSON.stringify(itemsList));
}

export { getPageCartItemsList, addPageCartItem, delPageCartItem };
