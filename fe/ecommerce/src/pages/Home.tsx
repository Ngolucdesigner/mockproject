import React, { useEffect, useState } from "react";
import Helmet from "../components/helmet/Helmet";

import { Container, Row, Col } from "reactstrap";
import "../styles/Home.scss";
import { Link } from "react-router-dom";
import { motion } from "framer-motion";
import Services from "../services/Services";
import ProductsList from "../components/UI/ProductsList";

import products from "../assets/data/products";
import { ProductProps } from "../model/productProps";
import Clock from "../components/UI/Clock";

const heroImg = require("../assets/images/hero-img.png");
const counterImg = require("../assets/images/counter-timer-img.png");
const Home = () => {
  const year = new Date().getFullYear();

  const [trendingProduct, setTrendingProduct] =
    useState<Array<ProductProps>>(products);

  const [BestSalesProduct, setBestSalesProduct] =
    useState<Array<ProductProps>>(products);

  const [mobileProduct, setMobileProduct] =
    useState<Array<ProductProps>>(products);

  const [wirelessProduct, setWirelessProduct] =
    useState<Array<ProductProps>>(products);

  const [watchProduct, setWatchProduct] =
    useState<Array<ProductProps>>(products);

  useEffect(() => {
    const filteredTrendingProduct = products.filter(
      (item) => item.category === "chair"
    );
    const filteredBestSalesProduct = products.filter(
      (item) => item.category === "sofa"
    );
    const filteredMobileProduct = products.filter(
      (item) => item.category === "mobile"
    );

    const filteredWirelessProduct = products.filter(
      (item) => item.category === "wireless"
    );

    const filteredWatchProduct = products.filter(
      (item) => item.category === "watch"
    );

    setTrendingProduct(filteredTrendingProduct);
    setBestSalesProduct(filteredBestSalesProduct);
    setMobileProduct(filteredMobileProduct);
    setWirelessProduct(filteredWirelessProduct);
    setWatchProduct(filteredWatchProduct);
  }, []);

  return (
    <Helmet title={"Home"}>
      <section className="hero__section">
        <Container>
          <Row>
            <Col lg="6" md="6">
              <div className="hero__content">
                <p className="hero__subtitle">Trending Product in {year}</p>
                <h2>Make your Interior More Mini Malistic & modern</h2>
                <p>
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Unde
                  corporis dolor illum accusantium necessitatibus natus beatae
                  cumque corrupti, quae voluptates.
                </p>
                <motion.button whileTap={{ scale: 1.2 }} className="buy__btn">
                  <Link to={"/shop"} className="link">
                    SHOP NOW
                  </Link>
                </motion.button>
              </div>
            </Col>
            <Col lg="6" md="6">
              <div className="hero__img">
                <img src={heroImg} alt="hero img" />
              </div>
            </Col>
          </Row>
        </Container>
      </section>

      <Services />
      <section className="trending__products">
        <Container>
          <Row>
            <Col lg="12" className="text-center">
              <h2 className="section__title">Trending Products</h2>
            </Col>
            <ProductsList products={trendingProduct} />
          </Row>
        </Container>
      </section>

      <section className="best__sales">
        <Container>
          <Row>
            <Col lg="12" className="text-center">
              <h2 className="section__title">Best Sales</h2>
            </Col>
            <ProductsList products={BestSalesProduct} />
          </Row>
        </Container>
      </section>

      <section className="timer-count">
        <Container>
          <Row>
            <Col lg="6" md="12" className="count__down-col">
              <div className="clock__top-content">
                <h4 className="text-white fs-6 mb-2">Limited Offers</h4>
                <h3 className="text-white fs-5 mb-3">Quality Armchair</h3>
              </div>
              <Clock />

              <motion.button
                whileTap={{ scale: 1.2 }}
                className="buy__btn store__btn"
              >
                <Link to={"/shop"} className="link">
                  Visit Store
                </Link>
              </motion.button>
            </Col>
            <Col lg="6" md="12" className="text-end counter__img">
              <img src={counterImg} alt="count img" />
            </Col>
          </Row>
        </Container>
      </section>

      <section className="new__arrivals">
        <Container>
          <Row>
            <Col lg="12" className="text-center mb-5">
              <h2 className="section__title">New Arrivals</h2>
            </Col>
            <ProductsList products={mobileProduct} />
            <ProductsList products={wirelessProduct} />
          </Row>
        </Container>
      </section>

      <section className="popular__category">
        <Container>
          <Row>
            <Col lg="12" className="text-center mb-5">
              <h2 className="section__title">Popular in Category</h2>
            </Col>

            <ProductsList products={watchProduct} />
          </Row>
        </Container>
      </section>
      
    </Helmet>
  );
};

export default Home;
