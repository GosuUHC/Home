import {
  sendNewStatus,
  getRole as getRoleTransport,
} from "../../transport/admin/transportAdmin";

export function updateOrderStatus(orderid, newStatus) {
  return sendNewStatus(orderid, newStatus);
}

export function getRole() {
  return getRoleTransport();
}
