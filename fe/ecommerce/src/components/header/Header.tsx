import React, { useEffect, useRef, useState } from "react";
import "./header.scss";

import { Container, Row } from "reactstrap";
import { Link } from "react-router-dom";
import { motion } from "framer-motion";
import { IoBagHandleOutline } from "react-icons/io5";
// import { FaRegHeart } from "react-icons/fa";

import { IoMdMenu } from "react-icons/io";

import { CiHeart } from "react-icons/ci";
import { useSelector } from "react-redux";

import { TReducers } from "../../redux/rootReducer";

const logo = require("../../assets/images/eco-logo.png");
const user = require("../../assets/images/user-icon.png");

interface NavLink {
  path: string;
  display: string;
}

const nav__Links: NavLink[] = [
  {
    path: "home",
    display: "Home",
  },
  {
    path: "shop",
    display: "Shop",
  },
  {
    path: "cart",
    display: "Cart",
  },
];

const Header = () => {
  const [activeLink, setActiveLink] = useState<string>(""); // State to track the active link

  const totalQuantity = useSelector(
    (state: TReducers) => state.cart.totalQuantity
  );

  const handleLinkClick = (path: string) => {
    setActiveLink(path);
  };

  const headerRef = useRef<HTMLDivElement>(null);
  const menuRef = useRef<HTMLDivElement>(null);

  const stickyHeaderFunc = () => {
    const handleScroll = () => {
      if (
        document.body.scrollTop > 80 ||
        document.documentElement.scrollTop > 80
      ) {
        headerRef.current?.classList.add("sticky__header");
      } else {
        headerRef.current?.classList.remove("sticky__header");
      }
    };

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  };
  useEffect(() => {
    stickyHeaderFunc();
  });

  const menuToggle = () => menuRef.current?.classList.toggle("active__menu");

  return (
    <header className="header" ref={headerRef}>
      <Container>
        <Row>
          <div className="nav__wrapper">
            <div className="logo">
              <img src={logo} alt="logo" />
              <div>
                <h1>
                  <Link to={"home"} className="link">
                    Anks
                  </Link>
                </h1>
                {/* <p>Since 1998</p> */}
              </div>
            </div>
            <div className="navigation" ref={menuRef} onClick={menuToggle}>
              <ul className="menu">
                {nav__Links.map((item, index) => (
                  <li className="nav__item" key={index}>
                    <Link
                      to={item.path}
                      className={
                        activeLink === item.path ? "nav__active link" : "link"
                      }
                      onClick={() => handleLinkClick(item.path)}
                    >
                      {item.display}
                    </Link>
                  </li>
                ))}
              </ul>
            </div>
            <div className="nav__icons">
              <span className="fav__icon">
                <CiHeart className="icon-item" />
                <span className="badge">1</span>
              </span>
              <span className="cart__icon">
                <IoBagHandleOutline className="icon-item" />
                <span className="badge">{totalQuantity}</span>
              </span>
              <span>
                <motion.img whileTap={{ scale: 1.2 }} src={user} alt="" />
              </span>

              <div className="mobile__menu">
                <span onClick={menuToggle}>
                  <IoMdMenu className="menu-icon" />
                </span>
              </div>
            </div>
          </div>
        </Row>
      </Container>
    </header>
  );
};

export default Header;
