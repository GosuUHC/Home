function ItemCard(props) {
  const { item, actionsComponent } = props;
  return (
    <div className="item-card">
      <div className="item-card__image">
        <img src={item.image} alt={item.name} />
      </div>
      <div className="item-card__info">
        <h2>{item.name}</h2>
        <p>{item.description}</p>
        <p>{item.price}</p>
      </div>
      <div className="item-card__actions">{actionsComponent}</div>
    </div>
  );
}

export default ItemCard;
