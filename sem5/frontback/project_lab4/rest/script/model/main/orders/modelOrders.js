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

function deleteOrder(ordText) {
    let ordid = ordText.match(/Order id: [\d]*/ig)[0];
    ordid = ordid.match(/(0|[1-9]\d*)$/)[0];
    return deleteOrderTransport(ordid);
}

export {
    getAllOrders,
    addOrder,
    deleteOrder,
};
