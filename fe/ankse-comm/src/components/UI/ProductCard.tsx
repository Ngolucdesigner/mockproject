import React from "react";
import { Col } from "reactstrap";
import { motion } from "framer-motion";
import "../../styles/ProductCard.scss";
import { Link } from "react-router-dom";
import { priceFormat } from "../../model/FormatVND";
import { useDispatch, useSelector } from "react-redux";
import { cartActions } from "../../redux/slices/cartSlice";

import { toast } from "react-toastify";
import { cartActionsHeart } from "../../redux/slices/cartHeart";
import { TReducers } from "../../redux/rootReducer";

type item = {
  id: string;
  imgUrl: any;
  productName: string;
  price: number;
  category: string;
};

const ProductCard = (props: item) => {
  const dispatch = useDispatch();
  const cartHeart:any = useSelector<TReducers>((state)=> state.heart.heartItems)
  
  const addToCart = () => {
    dispatch(
      cartActions.addItem({
        id: props.id,
        productName: props.productName,
        price: props.price,
        imgUrl: props.imgUrl,
      })
    );
    toast.success("Product added successfully");
  };

  const addToHeart = ()=>{

      const findTrue = cartHeart.find((item :any) => {
         return item.id===props.id
      })
      if(!findTrue){
        dispatch(
          cartActionsHeart.addItemToHeart({
            id: props.id,
            productName: props.productName,
            price: props.price,
            imgUrl: props.imgUrl
          })

        );
        toast.success("Product added successfully");
      }
      else{
        toast.error("Product added valid");
      }
  }

  return (
    <Col lg="3" md="4" className="mb-2">
      <div className="product__item">
        <div className="product__img">
          <motion.img
            whileHover={{ scale: 0.9 }}
            src={props.imgUrl}
            alt="sp1"
          />
        </div>
        <div className="p-2 product__info">
          <h3 className="product__name">
            <Link to={`/shop/${props.id}`} className="link">
              {props.productName}
            </Link>
          </h3>
          <span>{props.category.toUpperCase()}</span>
        </div>
        <div className="product__card-bottom d-flex align-item-center justify-content-between p-2">
          <span className="price">{priceFormat(props.price)}</span>

          <div className="product__card-action">
            <motion.span whileTap={{scale:1.2}} onClick={addToHeart}>
              <i className="ri-heart-fill"></i>{" "}
            </motion.span>

            <motion.span whileTap={{ scale: 1.2 }} onClick={addToCart}>
              <i className="ri-add-line"></i>
            </motion.span>
          </div>
        </div>
      </div>
    </Col>
  );
};

export default ProductCard;
