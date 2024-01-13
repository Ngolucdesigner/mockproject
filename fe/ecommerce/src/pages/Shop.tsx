import React, { ReactElement, useState } from "react";
import CommonSection from "../components/UI/CommonSection";
import Helmet from "../components/helmet/Helmet";
import { Col, Container, Row } from "reactstrap";

import "../styles/Shop.scss";
import { ProductProps } from "../model/productProps";
import products from "../assets/data/products";
import ProductsList from "../components/UI/ProductsList";

type productData = ProductProps;

const Shop = () => {
  const [productsData, setProductsData] =
    useState<Array<productData>>(products);

  const handleFilter = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const filterValue = event.target.value;

    console.log(filterValue);

    if (filterValue === "Filter By Category") {
      // const filterProducts = products.filter((item)=>item.category==="Filter By Category");
      setProductsData(products);
    }

    if (filterValue === "sofa") {
      const filterProducts = products.filter(
        (item) => item.category === "sofa"
      );
      setProductsData(filterProducts);
    }

    if (filterValue === "mobile") {
      const filterProducts = products.filter(
        (item) => item.category === "mobile"
      );
      setProductsData(filterProducts);
    }

    if (filterValue === "wireless") {
      const filterProducts = products.filter(
        (item) => item.category === "wireless"
      );
      setProductsData(filterProducts);
    }

    if (filterValue === "chair") {
      const filterProducts = products.filter(
        (item) => item.category === "chair"
      );
      setProductsData(filterProducts);
    }

    if (filterValue === "watch") {
      const filterProducts = products.filter(
        (item) => item.category === "watch"
      );
      setProductsData(filterProducts);
    }
  };

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    const searchItem = event.target.value;

    const searchedProducts = products.filter((item) =>
      item.productName.toLowerCase().includes(searchItem.toLowerCase())
    );
    setProductsData(searchedProducts);
  };

  return (
    <Helmet title="Shop">
      <CommonSection title={"Products"} />

      <section>
        <Container>
          <Row>
            <Col lg="3" md="3">
              <div className="filter__widget">
                <select onChange={handleFilter}>
                  <option>Filter By Category</option>
                  <option value="sofa">Sofa</option>
                  <option value="mobile">Mobile</option>
                  <option value="chair">Chair</option>
                  <option value="watch">Watch</option>
                  <option value="wireless">Wireless</option>
                </select>
              </div>
            </Col>
            <Col lg="3" md="3">
              <div className="filter__widget">
                <select>
                  <option>Soft By</option>
                  <option value="ascending">Ascending</option>
                  <option value="descending">Descending</option>
                </select>
              </div>
            </Col>
            <Col lg="6" md="6">
              <div className="search__box">
                <input
                  type="text"
                  placeholder="Search... "
                  onChange={handleSearch}
                />
                <span>
                  <i className="ri-search-line"></i>
                </span>
              </div>
            </Col>
          </Row>
        </Container>
      </section>

      <section>
        <Container>
          <Row>
            {productsData.length === 0 ? (
              <h1 className="text-center fs-4">No products are found</h1>
            ) : (
              <ProductsList products={productsData} />
            )}
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Shop;
