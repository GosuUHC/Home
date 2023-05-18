import { useCatalogue } from "vm/api.js";
import { CardElement } from "model/main/modelMain";
import ProductCard from "view/components/main/shared/ProductCard/ProductCard";
import AddToCartButton from "view/components/main/itemCatalogue/addToCartButton/AddToCartButton";
import Loading from "view/components/common/Loading";

function PageItemCatalogue(props) {
  const { tableData, loaded, handleAdd } = useCatalogue();

  if (!loaded) {
    return <Loading />;
  }

  const renderCatalogue = tableData.map((data, i) => {
    const item = new CardElement();
    item.set(data);
    return (
      <ProductCard
        key={i}
        title={item.getRemainingFields(["id", "img", "price", "itemType"], true)}
        description={item.getRemainingFields(["img", "manufacturer", "name"])}
        price={item.getField("price")}
        img={item.getField("img")}
        actions={<AddToCartButton index={i} handleAdd={handleAdd} />}
      />
    );
  });

  return <>{renderCatalogue}</>;
}

export default PageItemCatalogue;
