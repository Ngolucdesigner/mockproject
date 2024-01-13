import React from "react";
import { Link } from "react-router-dom";
import { Container, Row, Col, ListGroup, ListGroupItem } from "reactstrap";
import "./Footer.scss"


const Footer = () => {

    const year = new Date().getFullYear();
  return (
    <footer className="footer">
      <Container>
        <Row>
          <Col lg="4" md='6' className="mb-4">
            <div className="logo">
              {/* <img src={logo} alt="logo" /> */}
              <div>
                <h1 className="text-white">
                  <Link to={"home"} className="link">
                    Anks
                  </Link>
                </h1>
              </div>
              <p className="footer__text mt-4">
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Vitae
                quidem, labore id enim sed officiis rerum fugit corporis magnam
                commodi.
              </p>
            </div>
          </Col>
          <Col lg="3" md='3' className="mb-4">
            <div className="footer__quick-links">
              <h4 className="quick__links-title">Top Categories</h4>
              <ListGroup>
                <ListGroupItem className="ps-0 border-0">
                  <Link to={"#"} className="link">
                    Mobile Phones
                  </Link>
                </ListGroupItem>

                <ListGroupItem className="ps-0 border-0">
                  <Link to={"#"} className="link">
                    Modern Sofa
                  </Link>
                </ListGroupItem>

                <ListGroupItem className="ps-0 border-0">
                  <Link to={"#"} className="link">
                    Arm Chair
                  </Link>
                </ListGroupItem>

                <ListGroupItem className="ps-0 border-0">
                  <Link to={"#"} className="link">
                    Smart Watch
                  </Link>
                </ListGroupItem>
              </ListGroup>
            </div>
          </Col>
          <Col lg="2" md='3' className="mb-4">
            <div className="footer__quick-links">
              <h4 className="quick__links-title">Useful Links</h4>
              <ListGroup>
                <ListGroupItem className="ps-0 border-0">
                  <Link to={"/shop"} className="link">
                    Shop
                  </Link>
                </ListGroupItem>

                <ListGroupItem className="ps-0 border-0">
                  <Link to={"/cart"} className="link">
                    Cart
                  </Link>
                </ListGroupItem>

                <ListGroupItem className="ps-0 border-0">
                  <Link to={"/login"} className="link">
                    Login
                  </Link>
                </ListGroupItem>

                <ListGroupItem className="ps-0 border-0">
                  <Link to={"#"} className="link">
                    Privacy Policy
                  </Link>
                </ListGroupItem>
              </ListGroup>
            </div>
          </Col>
          <Col lg="3" md='4'>
            <div className="footer__quick-links">
              <h4 className="quick__links-title">Contact</h4>
              <ListGroup className="footer__contact">
                <ListGroupItem className="ps-0 border-0 d-flex align-items-center gap-2">
                  <span>
                    <i className="ri-map-pin-line"></i>
                  </span>
                  <p>123 ho sy suong phuong hung binh tp vinh nghe an</p>
                </ListGroupItem>

                <ListGroupItem className="ps-0 border-0 d-flex align-items-center gap-2">
                  <span>
                    <i className="ri-phone-line"></i>
                  </span>
                  <p>+84123123123</p>
                </ListGroupItem>

                <ListGroupItem className="ps-0 border-0 d-flex align-items-center gap-2">
                  <span>
                    <i className="ri-mail-line"></i>
                  </span>
                  <p>ankstech.designer@gmail.com</p>
                </ListGroupItem>
              </ListGroup>
            </div>
          </Col>

          <Col lg="12"><p className="footer__copyright"> Copyright {year} by Anks. All right reserved.</p></Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
