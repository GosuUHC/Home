import React, { useState } from "react";
import { IconButton, Typography } from "@mui/material";
import {
  AddCircleOutline,
  RemoveCircleOutline,
  Delete,
} from "@mui/icons-material";
import { CheckCircleOutline, CancelOutlined } from "@mui/icons-material";
import styles from "./ItemCartActions.module.css";

const ItemCartActions = ({
  index,
  handleChangeQuantity,
  handleAddOrder,
  handleDeleteFromCart,
  quantity,
}) => {
  const [isOrdering, setIsOrdering] = useState(false);
  const [orderSuccess, setOrderSuccess] = useState(false);
  const [loaded, setLoaded] = useState(false);

  const increaseQuantity = () => {
    handleChangeQuantity(index, quantity + 1);
  };

  const decreaseQuantity = () => {
    handleChangeQuantity(index, quantity - 1);
  };

  const removeFromCart = () => {
    handleDeleteFromCart(index);
  };

  const placeOrder = async () => {
    setIsOrdering(true);
    const isSuccess = await handleAddOrder(index);
    setOrderSuccess(isSuccess);
    setLoaded(true);
  };

  return (
    <div>
      <div className={styles.quantityContainer}>
        <IconButton
          aria-label="Увеличить количество"
          onClick={increaseQuantity}
        >
          <AddCircleOutline />
        </IconButton>

        <Typography variant="subtitle1">{quantity}</Typography>

        <IconButton
          aria-label="Уменьшить количество"
          onClick={decreaseQuantity}
        >
          <RemoveCircleOutline />
        </IconButton>
      </div>

      <div>
        <IconButton aria-label="Удалить из корзины" onClick={removeFromCart}>
          <Delete />
        </IconButton>

        <IconButton
          aria-label="Совершить заказ"
          onClick={placeOrder}
          disabled={isOrdering}
        >
          {loaded ? (
            orderSuccess ? (
              <CheckCircleOutline className={styles.successIcon} />
            ) : (
              <CancelOutlined className={styles.errorIcon} />
            )
          ) : (
            <AddCircleOutline className={styles.orderIcon} />
          )}
        </IconButton>
      </div>
    </div>
  );
};

export default ItemCartActions;
