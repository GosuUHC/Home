import { fetchWrapper } from "transport/request";

const protocol = "http";
const host = "localhost";
const port = "8080";
const name = "rest-1";
const domain = `${protocol}://${host}:${port}/${name}`;

export async function sendNewStatus(orderid, newStatus) {
  const body = {
    status: newStatus,
  };
  let response = await fetchWrapper(
    "PATCH",
    `${domain}/api/v1/orders/${orderid}`,
    body,
    {}
  );
  return response;
}

export async function getPagedOrders(page, pageSize) {
  let response = await fetchWrapper(
    "GET",
    `${domain}/api/v1/orders/${page}/${pageSize}`
  );

  console.log(response);

  return response;
}

export async function getPagedOrdersByUsername(username, page, pageSize) {
  let response = await fetchWrapper(
    "GET",
    `${domain}/api/v1/orders/${username}/${page}/${pageSize}`
  );

  console.log(response);

  return response;
}

export function getRole() {
  return localStorage.getItem("role");
}
