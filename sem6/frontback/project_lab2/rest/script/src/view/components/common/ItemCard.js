const ItemCard = ({ image, name, description, controls }) => {
  return (
    <div className="item-card">
      <div className="item-image">
        <img src={image} alt={name} />
      </div>
      <div className="item-details">
        <h3 className="item-name">{name}</h3>
        <p className="item-description">{description}</p>
      </div>
      <div className="item-controls">{controls}</div>
    </div>
  );
};

export default ItemCard;
