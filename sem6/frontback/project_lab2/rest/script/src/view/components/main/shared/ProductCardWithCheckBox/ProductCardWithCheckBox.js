import React from "react";
import { Checkbox } from "@mui/material";
import ProductCard from "../ProductCard/ProductCard";

const ProductCardWithCheckbox = (props) => {
  return (
    <div>
      <Checkbox
        checked={props.checked}
        onChange={props.handleCheckboxChange}
      />
      <ProductCard {...props} />
    </div>
  );
};

export default ProductCardWithCheckbox;
