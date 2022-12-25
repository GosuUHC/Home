import { getCatalogueItems } from "../../../transport/main/transportCatalogueItems.js"


function getItems(itemsType) {
    return getCatalogueItems(itemsType);
}

export { getItems };
