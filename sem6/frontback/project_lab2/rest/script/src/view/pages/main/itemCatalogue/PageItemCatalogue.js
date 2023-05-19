import { useCatalogue } from "vm/api.js";
import ProductCard from "view/components/main/shared/ProductCard/ProductCard";
import AddToCartButton from "view/components/main/itemCatalogue/addToCartButton/AddToCartButton";
import Loading from "view/components/common/Loading";

function PageItemCatalogue(props) {
  const { tableData, loaded, handleAdd, addedToCartIndices } = useCatalogue();

  if (!loaded) {
    return <Loading />;
  }

  const renderCatalogue = tableData.map((item, i) => {
    const otherFields = Object.entries(item.other)
      .map(([key, value]) => `${key}: ${value}`)
      .join(", ");
    const isAddedToCart = addedToCartIndices.includes(i);
    return (
      <ProductCard
        key={i}
        title={`${item.manufacturer} ${item.name}`}
        description={[otherFields, `id: ${item.id}`, `price: ${item.price}`]}
        price={item.price}
        img={item.img}
        actions={
          <AddToCartButton
            isAddedToCart={isAddedToCart}
            index={i}
            handleAdd={handleAdd}
          />
        }
      />
    );
  });

  return <>{renderCatalogue}</>;
}

export default PageItemCatalogue;
