async function getAllOrders() {
    let response = await myXmlRequest("GET", "api/orders", {}, {});
    return response;
}

async function addOrder(itemid, itemType, itemCount) {
    const itemData = {
        "id": itemid,
        "type": itemType,
        "count": itemCount
    };
    let response = await myXmlRequest("POST", "api/orders", itemData, {});
    return response;
}

async function deleteOrder(ordid) {
    const ordData = { "id": ordid };
    let response = await myXmlRequest("DELETE", "api/orders", {}, ordData);
    return response;
}

export {
    getAllOrders,
    addOrder,
    deleteOrder
};
