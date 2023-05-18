import { CardElement } from "model/main/modelMain";
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

  const renderItemCart = itemsList.map((pageCartItem, i) => {
    const item = new CardElement();
    item.set(pageCartItem);
    return (
      <ProductCard
        key={i}
        title={item.getRemainingFields(["id", "img", "price", "itemType"], true)}
        description={item.getRemainingFields(["img", "manufacturer", "name"])}
        price={item.getField("price")}
        img={item.getField("img")}
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
