import { fetchWrapper } from "../request.js";

let protocol = "http";
let host = "localhost";
let port = "8080";
let name = "rest-1";
let domain = `${protocol}://${host}:${port}/${name}`;

async function getAllOrders() {
  let response = await fetchWrapper("GET", `${domain}/api/v1/orders`, {}, {});
  return response;
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
