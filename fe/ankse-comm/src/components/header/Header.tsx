import React, { useEffect, useRef } from "react";
import "./header.scss";

import { Container, Row } from "reactstrap";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import { IoBagHandleOutline } from "react-icons/io5";
// import { FaRegHeart } from "react-icons/fa";

import { IoMdMenu } from "react-icons/io";

import { CiHeart } from "react-icons/ci";
import { useDispatch, useSelector } from "react-redux";

import { TReducers } from "../../redux/rootReducer";
import { login } from "../../redux/slices/isLogin";

import * as request from "../../Utils/request";

import { toast } from "react-toastify";
import { loginInfo } from "../../model/login";

const logo = require("../../assets/images/eco-logo.png");
const user = require("../../assets/images/user-icon.png");

const nav__Links = [
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
  const dispatch = useDispatch();

  // Kiểm tra trạng thái đăng nhập từ localStorage
  const isLoggedIn = useSelector<TReducers>((state) => state.login.isLogin);

  const avatar :loginInfo | any = useSelector<TReducers>((state) => state.login.loginState);




  const totalQuantity = useSelector(
    (state: TReducers) => state.cart.totalQuantity
  );

  const totalHeart = useSelector(
    (state: TReducers) => state.heart.totalQuantity
  );

  const headerRef = useRef<HTMLDivElement>(null);
  const menuRef = useRef<HTMLDivElement>(null);

  const profileActionRef = useRef<HTMLDivElement>(null);

  const navigate = useNavigate();

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
  const toggleProfileActions = () =>
    profileActionRef.current?.classList.toggle("show__profileActions");

  const navigateToCart = () => {
    navigate("/cart");
  };

  const config = {
    // withCredentials: true,
    "Content-Type": "application/json",
    // Authorization: "Bearer " + getDataFromCookie("user"),
    // Authorization: "Basic " + localStorage.getItem("cookie"),
    // 'Access-Control-Allow-Origin': false ,
  };

  const handleLogout = async () => {
    try {
      await request
        .post1("auth/sign-out", { headers: config })
        .then(() => {
          
          dispatch(login.clearInfo());
          toast.success("You've been signed out!", {
            position: toast.POSITION.TOP_CENTER,
          });
        });
    } catch (err) {
        console.log(err)
    }

    
  };


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
                    <NavLink
                      to={item.path}
                      className={(navClass) =>
                        navClass.isActive ? "nav__active link" : "link"
                      }
                    >
                      {item.display}
                    </NavLink>
                  </li>
                ))}
              </ul>
            </div>
            <div className="nav__icons">
              <span className="fav__icon">
                <CiHeart className="icon-item" />
                <span className="badge">{totalHeart}</span>
              </span>
              <span className="cart__icon">
                <IoBagHandleOutline
                  className="icon-item"
                  onClick={navigateToCart}
                />
                <span className="badge">{totalQuantity}</span>
              </span>
              <div className="profile">
                <span onClick={toggleProfileActions}>
                  <motion.img
                    whileTap={{ scale: 1.2 }}
                    src={isLoggedIn ? avatar.avatar.url :user}
                    alt="User Avatar"
                  />
                </span>

                <div
                  className="profile__action"
                  ref={profileActionRef}
                  onClick={toggleProfileActions}
                >
                  {isLoggedIn ? (
                    <div className="d-flex align-items-center justify-content-center flex-column gap-1">
                        <span>Hi! {avatar.username}</span>
                       <span onClick={handleLogout}>Logout</span>
                       
                    </div>
                  ) : (
                    <div className="d-flex align-items-center justify-content-center flex-column">
                      <Link to={"/signup"} className="link">
                        Sign up
                      </Link>
                      <Link to={"/login"} className="link">
                        Login
                      </Link>
                    </div>
                  )}
                </div>
              </div>

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
