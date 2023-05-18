import React from "react";
import { Card, CardMedia, CardContent, Typography } from "@mui/material";
import styles from "./ProductCard.module.css";

const ProductCard = ({ img, title, description, price, actions }) => {
  const descriptionItems = description.map((item, index) => (
    <Typography key={index} variant="body2" className={styles.descriptionItem}>
      {item}
    </Typography>
  ));

  return (
    <Card className={styles.card}>
      <CardMedia
        component="img"
        src={img}
        alt={title}
        className={styles.photo}
      />

      <CardContent className={styles.content}>
        <div className={styles.header}>
          <Typography variant="h7" className={styles.title}>
            {title}
          </Typography>
        </div>

        <div className={styles.description}>{descriptionItems}</div>

        <div className={styles.actions}>
          <Typography variant="subtitle1" className={styles.price}>
            {`${price} ₽`}
          </Typography>
          {actions}
        </div>
      </CardContent>
    </Card>
  );
};

export default ProductCard;
