import React from "react";
import ProductCard from "./ProductCard";


import { ProductProps } from "../../model/productProps";
  
  type data = {
    products: ProductProps[];
  };

const ProductsList = (props: data) => {
  return (
    <>
      {props.products.map((item, index) => (
        <ProductCard
          key={item.id}
          id={item.id}
          imgUrl={item.imgUrl}
          productName={item.productName}
          price={item.price}
          category={item.category}
        />
      ))}
    </>
  );
};

export default ProductsList;
