import { fetchWrapper } from "../request.js";

let protocol = "http";
let host = "localhost";
let port = "8080";
let name = "rest-1";
let domain = `${protocol}://${host}:${port}/${name}`;

export async function sendNewStatus(orderid, newStatus) {
  const body = {
    "status": newStatus
  };
  let response = await fetchWrapper(
    "PATCH",
    `${domain}/api/v1/orders/${orderid}`,
    body,
    {}
  );
  return response;
}

export function getRole() {
  return localStorage.getItem("role");
}
