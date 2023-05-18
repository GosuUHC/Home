import { useState } from "react";
import { Button } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import styles from "./AddToCartButton.module.css";

const AddToCartButton = ({ index, handleAdd }) => {
  const [isAddedToCart, setIsAddedToCart] = useState(false);

  const handleButtonClick = () => {
    if (!isAddedToCart) {
      handleAdd(index);
      setIsAddedToCart(true);
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
