import {
  sendNewStatus,
  getRole as getRoleTransport,
  getPagedOrders,
  getPagedOrdersByUsername,
} from "transport/admin/transportAdmin";

export const pageSize = 10;

export function updateOrderStatus(orderid, newStatus) {
  return sendNewStatus(orderid, newStatus);
}

export function getAllOrdersPaged(page) {
  return getPagedOrders(page, pageSize);
}

export function getAllOrdersPagedByUsername(username, page) {
  return getPagedOrdersByUsername(username, page, pageSize);
}

export function getRole() {
  return getRoleTransport();
}
