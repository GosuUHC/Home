import ProductCard from "view/components/main/shared/ProductCard/ProductCard";
import { useItemCart } from "vm/api";
import Loading from "view/components/common/Loading";
import ItemCartActions from "view/components/main/itemCart/ItemCartActions/ItemCartActions";

function PageItemCart(props) {
  const {
    itemsList,
    loaded,
    counts,
    handleCount,
    handleDelete,
    handleOrderCreation,
  } = useItemCart();

  if (!loaded) {
    return <Loading />;
  }

  const renderItemCart = itemsList.map((item, i) => {
    const otherFields = Object.entries(item.other)
      .map(([key, value]) => `${key}: ${value}`)
      .join(", ");

    return (
      <ProductCard
        key={i}
        title={`${item.manufacturer} ${item.name}`}
        description={[
          otherFields,
          `id: ${item.id}`,
          `price: ${item.price}`,
          `type: ${item.itemType}`,
        ]}
        price={item.price}
        img={item.img}
        actions={
          <ItemCartActions
            index={i}
            handleChangeQuantity={handleCount}
            handleAddOrder={handleOrderCreation}
            handleDeleteFromCart={handleDelete}
            quantity={counts[i]}
          />
        }
      />
    );
  });

  return <>{renderItemCart}</>;
}

export default PageItemCart;
