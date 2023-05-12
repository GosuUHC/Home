import {
  getAllOrders as getAllOrdersTransport,
  addOrder as addOrderTransport,
  deleteOrder as deleteOrderTransport,
} from "transport/main/transportOrders.js";

import { ordersNotificationsManager } from "transport/websocket";

function getAllOrders() {
  return getAllOrdersTransport();
}

function addOrder(itemid, itemType, itemCount) {
  return addOrderTransport(itemid, itemType, itemCount);
}

function deleteOrder(ordid) {
  return deleteOrderTransport(ordid);
}

function manageNotifications(login) {
  const { initialize, subscribe, unsubscribe } = ordersNotificationsManager;
  initialize(login);

  return {
    subscribe,
    unsubscribe,
  };
}

export { getAllOrders, addOrder, deleteOrder, manageNotifications };
