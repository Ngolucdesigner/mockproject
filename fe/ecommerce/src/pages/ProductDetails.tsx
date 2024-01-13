import React, { useState } from "react";
import { useParams } from "react-router-dom";
import products from "../assets/data/products";
import Helmet from "../components/helmet/Helmet";
import CommonSection from "../components/UI/CommonSection";
import { Col, Container, Row } from "reactstrap";

import { priceFormat } from "../model/FormatVND";
import { motion } from "framer-motion";
import "../styles/ProductDetails.scss";
import { time } from "console";

const ProductDetails = () => {
  const [tab, setTab] = useState("desc");

  const { id } = useParams();
  const product = products.find((item) => item.id === id);
    console.log(product)
  const {
    productName,
    imgUrl,
    price,
    description,
    avgRating,
    shortDesc,
    reviews,
  }: any = product;

  return (
    <Helmet title={productName}>
      <CommonSection title={productName} />
      <section className="pt-0">
        <Container>
          <Row>
            <Col lg="6">
              <img src={imgUrl} alt="" />
            </Col>

            <Col lg="6">
              <div className="product_details">
                <h2>{productName}</h2>
                <div className="product__rating d-flex align-item-center gap-5 mb-3">
                  <div>
                    <span>
                      <i className="ri-star-s-fill"></i>
                    </span>
                    <span>
                      <i className="ri-star-s-fill"></i>
                    </span>
                    <span>
                      <i className="ri-star-s-fill"></i>
                    </span>
                    <span>
                      <i className="ri-star-s-fill"></i>
                    </span>
                    <span>
                      <i className="ri-star-half-s-fill"></i>
                    </span>
                  </div>
                  <p>{<span>{avgRating}</span>}ratings</p>
                </div>
                <span className="product__price">{priceFormat(price)}</span>
                <p className="mt-3">{shortDesc}</p>
                <motion.button whileTap={{ scale: 1.2 }} className="buy__btn">
                  Add to Cart
                </motion.button>
              </div>
            </Col>
          </Row>
        </Container>
      </section>

      <section>
        <Container>
          <Row>
            <Col lg="12">
              <div className="tap__wrapper d-flex align-item-center gap-5">
                <h6
                  className={`${tab === "desc" ? "active__tab" : ""}`}
                  onClick={() => {
                    setTab("desc");
                  }}
                >
                  Description
                </h6>
                <h6
                  className={`${tab === "rev" ? "active__tab" : ""}`}
                  onClick={() => {
                    setTab("rev");
                  }}
                >
                  Review ({reviews.length})
                </h6>
              </div>

              {tab === "desc" ? (
                <div className="tab__content mt-5">
                  <p>{description}</p>
                </div>
              ) : (
                <div className="product__review">
                  <div className="review__wrapper">
                    <ul>
                      {reviews?.map( (item:any, index: number)=> {
                        
                          <li key={index}>
                            <span>{item.rating}</span>
                            <p>{item.text}</p>
                          </li>
                          
                       
                      })}
                    </ul>
                  </div>
                </div>
              )}
            </Col>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default ProductDetails;
