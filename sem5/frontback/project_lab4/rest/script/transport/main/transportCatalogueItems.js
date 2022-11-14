async function getCatalogueItems(itemsType) {
    const itemTypeFilter = {
        type: itemsType
    };
    let response = await myXmlRequest("GET", "api/items", {}, itemTypeFilter);
    return response;
}

export { getCatalogueItems };
