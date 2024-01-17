import React from "react";
import { Col, Container, Row } from "reactstrap";

import Spinner from "react-bootstrap/Spinner";

import "../styles/Dashboard.scss";
import useGetData from "../custom-hooks/useGetData";

const Dashboard = () => {
  const totalElementsProduct = useGetData().totalElementsProduct;
  const totalElementsAccount = useGetData().totalElementsAccount;

  return (
    <>
      <section>
        <Container>
          <Row>
            <Col className="lg-3">
              <div className="revenue__box">
                <h5>Total Sales</h5>
                <span>$1234</span>
              </div>
            </Col>
            <Col className="lg-3">
              <div className="order__box">
                <h5>Orders</h5>
                <span>56775</span>
              </div>
            </Col>
            <Col className="lg-3">
              <div className="product__box">
                <h5>Total Products</h5>
                {totalElementsProduct ? (
                  <span>{totalElementsProduct}</span>
                ) : (
                  <Spinner animation="border" variant="info" />
                )}
              </div>
            </Col>
            <Col className="lg-3">
              <div className="user__box">
                <h5>Total Users</h5>
                {totalElementsAccount ? (
                  <span>{totalElementsAccount}</span>
                ) : (
                  <Spinner animation="border" variant="info" />
                )}
              </div>
            </Col>
          </Row>
        </Container>
      </section>
    </>
  );
};

export default Dashboard;
