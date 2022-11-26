async function getAllOrders() {
    let response = await myXmlRequest("GET", "api/v1/orders", {}, {});
    return response;
}

async function addOrder(itemid, itemType, itemCount) {
    const itemData = {
        itemid,
        itemType,
        itemCount,
    };
    let response = await myXmlRequest("POST", "api/v1/orders", itemData, {}, true);
    return response;
}

async function deleteOrder(ordid) {
    const ordData = { "id": ordid };
    let response = await myXmlRequest("DELETE", "api/v1/orders", {}, ordData);
    return response;
}

export {
    getAllOrders,
    addOrder,
    deleteOrder,
};
