import { fetchWrapper } from "transport/request";

const protocol = "http";
const host = "localhost";
const port = "8080";
const name = "rest-1";
const domain = `${protocol}://${host}:${port}/${name}`;

async function getCatalogueItems(itemsType) {
  const itemTypeFilter = {
    type: itemsType,
  };
  let response = await fetchWrapper(
    "GET",
    `${domain}/api/v1/items`,
    {},
    itemTypeFilter
  );
  return response;
}

export { getCatalogueItems };
