import React, { useState } from "react";
import { motion } from "framer-motion";
import {
  Carousel,
  CarouselItem,
  CarouselControl,
  CarouselIndicators,
  CarouselCaption,
  Container,
  Row,
  Col,
} from "reactstrap";
import { Link } from "react-router-dom";
import { homeData } from "../../assets/data/home";

const heroImg = require("../../assets/images/hero-img.png");

const items = [
  {
    src: heroImg,
    title: homeData.title,
    text: homeData.text,
    caption: "h1",
    captionHeader: "abc",
    backgroundColor: "#e2f2b2",
    key: 1,
  },
  {
    src: heroImg,
    title: homeData.title,
    text: homeData.text,
    caption: "h2",
    captionHeader: "abc",
    backgroundColor: "#fefefe",
    key: 2,
  },
  {
    src: heroImg,
    title: homeData.title,
    text: homeData.text,
    caption: "h3",
    captionHeader: "abc",
    backgroundColor: "#d6e5fb",
    key: 3,
  },
];

const HeroSection = () => {
  const args: [] = [];

  const [activeIndex, setActiveIndex] = useState(0);
  const [animating, setAnimating] = useState(false);

  const next = () => {
    if (animating) return;
    const nextIndex = activeIndex === items.length - 1 ? 0 : activeIndex + 1;
    setActiveIndex(nextIndex);
  };

  const previous = () => {
    if (animating) return;
    const nextIndex = activeIndex === 0 ? items.length - 1 : activeIndex - 1;
    setActiveIndex(nextIndex);
  };

  const goToIndex = (newIndex: any) => {
    if (animating) return;
    setActiveIndex(newIndex);
  };

  const year = new Date().getFullYear();

  const slides = items.map((item) => {
    return (
      <CarouselItem
        onExiting={() => setAnimating(true)}
        onExited={() => setAnimating(false)}
        key={item.key}
      >
        <section className="hero__section" style={ {background: item.backgroundColor }}>
          <Container>
            <Row>
              <Col lg="6" md="6">
                <div className="hero__content">
                  <p className="hero__subtitle">Trending Product in {year}</p>
                  <h2>{item.title}</h2>
                  <p>{item.text}</p>
                  <motion.button whileTap={{ scale: 1.2 }} className="buy__btn">
                    <Link to={"/shop"} className="link">
                      SHOP NOW
                    </Link>
                  </motion.button>
                </div>
              </Col>

              <Col lg="6" md="6">
                <div className="hero__img">
                  <img src={item.src} alt="hero img" />
                </div>
              </Col>
            </Row>
          </Container>
        </section>
        <CarouselCaption
          captionText={item.caption}
          captionHeader={item.captionHeader}
        />
      </CarouselItem>
    );
  });

  return (
    <Carousel
     
      activeIndex={activeIndex}
      next={next}
      previous={previous}
      {...args}
      interval= {5000}
    >
      <CarouselIndicators
        items={items}
        activeIndex={activeIndex}
        onClickHandler={goToIndex}
      />
      {slides}
      <CarouselControl
        direction="prev"
        directionText="Previous"
        onClickHandler={previous}
      />
      <CarouselControl
        direction="next"
        directionText="Next"
        onClickHandler={next}
      />
    </Carousel>
  );
};

export default HeroSection;
