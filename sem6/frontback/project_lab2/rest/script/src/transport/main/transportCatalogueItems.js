import { ItemElement } from "model/dto/ItemElement";
import { fetchWrapper } from "transport/request";

const protocol = "http";
const host = "localhost";
const port = "8080";
const name = "rest-1";
const domain = `${protocol}://${host}:${port}/${name}`;

function convert(response) {
  const catalogueData = response.map((elem) => {
    const item = Object.assign({}, ItemElement);
    item.id = elem.id;
    item.img = elem.img;
    item.manufacturer = elem.manufacturer;
    item.name = elem.name;
    item.price = elem.price;

    item.other = {};
    for (const key in elem) {
      if (!item.hasOwnProperty(key)) {
        item.other[key] = elem[key];
      }
    }
    return item;
  });
  return catalogueData;
}

async function getCatalogueItems(itemsType) {
  const itemTypeFilter = { type: itemsType };

  const response = await fetchWrapper(
    "GET",
    `${domain}/api/v1/items`,
    {},
    itemTypeFilter
  );

  return convert(response);
}

export { getCatalogueItems };
