import { fetchWrapper } from "../request.js";


let protocol = "http";
let host = "localhost";
let port = "8080";
let name = "rest-1";
let domain = `${protocol}://${host}:${port}/${name}`;


async function getCatalogueItems(itemsType) {
    const itemTypeFilter = {
        type: itemsType
    };
    let response = await fetchWrapper("GET", `${domain}/api/v1/items`, {}, itemTypeFilter);
    return response;
}

export { getCatalogueItems };
