var modelMain = (function () {

    function getItems(catalogueSelectedValue, fillFunc) {
        var itemTypeFilter = {
            "type": catalogueSelectedValue
        }
        myXmlRequest("GET", "api/items", {}, fillFunc, itemTypeFilter);
    }

    function getOrders(fillFunc) {
        myXmlRequest("GET", "api/orders", {}, fillFunc);
    }

    function addOrder(itemID, itemType, count) {
        var item = {
            "id": itemID,
            "type": itemType,
            "count": count
        }
        myXmlRequest("POST", "api/orders", item, alert);
    }

    function deleteOrder(ord = {}) {
        var ordidFilter = {
            "id": ord.id
        };
        console.log(ordidFilter);

        myXmlRequest("DELETE", "api/orders", {}, alert, ordidFilter);
    }

    return {
        getOrders: getOrders,
        addOrder: addOrder,
        getItems: getItems,
        deleteOrder: deleteOrder
    }
}
)();
