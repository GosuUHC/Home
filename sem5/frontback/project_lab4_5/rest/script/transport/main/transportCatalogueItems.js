async function getCatalogueItems(itemsType) {
    const itemTypeFilter = {
        type: itemsType
    };
    let response = await myXmlRequest("GET", "api/v1/items", {}, itemTypeFilter);
    return response;
}

export { getCatalogueItems };
