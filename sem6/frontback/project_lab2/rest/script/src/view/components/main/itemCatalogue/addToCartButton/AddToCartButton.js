import { Button } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import styles from "./AddToCartButton.module.css";

const AddToCartButton = ({ index, handleAdd, isAddedToCart }) => {
  const handleButtonClick = () => {
    if (!isAddedToCart) {
      handleAdd(index);
    }
  };

  return (
    <Button
      variant="contained"
      onClick={handleButtonClick}
      className={`${styles.button} ${isAddedToCart ? styles.added : ""}`}
      startIcon={isAddedToCart ? <CheckCircleIcon /> : <ShoppingCartIcon />}
      style={{ backgroundColor: isAddedToCart ? "green" : "" }}
    >
      {isAddedToCart ? "В корзине" : "В корзину"}
    </Button>
  );
};

export default AddToCartButton;
