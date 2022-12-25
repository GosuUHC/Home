import {
    getAllOrders as getAllOrdersTransport,
    addOrder as addOrderTransport,
    deleteOrder as deleteOrderTransport,
} from "../../../transport/main/transportOrders.js";


function getAllOrders() {
    return getAllOrdersTransport();
}

function addOrder(itemid, itemType, itemCount) {
    return addOrderTransport(itemid, itemType, itemCount);
}

function deleteOrder(ordid) {
    return deleteOrderTransport(ordid);
}

export {
    getAllOrders,
    addOrder,
    deleteOrder,
};
