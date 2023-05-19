import { fetchWrapper } from "transport/request";
import { OrderElement } from "model/dto/OrderElement";
import { ItemElement } from "model/dto/ItemElement";

const protocol = "http";
const host = "localhost";
const port = "8080";
const name = "rest-1";
const domain = `${protocol}://${host}:${port}/${name}`;

function convert(response) {
  const ordersData = response.map((elem) => {
    const order = Object.assign({}, OrderElement);
    order.id = elem.id;
    order.itemCount = elem.itemCount;
    order.itemType = elem.itemType;
    order.itemId = elem.itemid;
    order.price = elem.price;
    order.status = elem.status;
    order.userLogin = elem.userLogin;

    const item = Object.assign({}, ItemElement);
    const itemData = elem.item;
    item.id = itemData.id;
    item.img = itemData.img;
    item.manufacturer = itemData.manufacturer;
    item.name = itemData.name;
    item.price = itemData.price;

    item.other = {};
    for (const key in itemData) {
      if (!item.hasOwnProperty(key)) {
        item.other[key] = itemData[key];
      }
    }

    order.item = item;

    return order;
  });

  return ordersData;
}

async function getAllOrders() {
  const response = await fetchWrapper("GET", `${domain}/api/v1/orders`, {}, {});

  return convert(response);
}

async function addOrder(itemid, itemType, itemCount) {
  const itemData = {
    itemid,
    itemType,
    itemCount,
  };
  let response = await fetchWrapper(
    "POST",
    `${domain}/api/v1/orders`,
    itemData,
    {},
    true
  );
  return response;
}

async function deleteOrder(ordid) {
  const ordData = { id: ordid };
  let response = await fetchWrapper(
    "DELETE",
    `${domain}/api/v1/orders`,
    {},
    ordData
  );
  return response;
}

export { getAllOrders, addOrder, deleteOrder };
