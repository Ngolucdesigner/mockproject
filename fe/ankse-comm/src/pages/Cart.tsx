import React from "react";
import "../styles/Cart.scss";
import Helmet from "../components/helmet/Helmet";
import CommonSection from "../components/UI/CommonSection";
import { Col, Container, Row } from "reactstrap";
import { useDispatch, useSelector } from "react-redux";
import { TReducers } from "../redux/rootReducer";
import { priceFormat } from "../model/FormatVND";
import { useNavigate } from "react-router-dom";
import { cartActions } from "../redux/slices/cartSlice";
import { motion } from "framer-motion";
import { toast } from "react-toastify";
import { GrSubtract } from "react-icons/gr";
import { FaPlus } from "react-icons/fa6";

type itemCart = {
  id: any;
  imgUrl: any;
  price: number;
  
  quantity: number;
  productName: any;
  file: { url: string } | any;
  detail: (id: any) => void;
  delete: (id: any) => void;
};

const PropsCart = (props: itemCart) => {
  const dispatch = useDispatch();
  // Hàm để tăng số lượng sản phẩm
  const increment = () => {
    // Cập nhật số lượng sản phẩm trong Redux store
    dispatch(cartActions.updateQuantity({ id: props.id, quantity: props.quantity + 1 }));
  };

  // Hàm để giảm số lượng sản phẩm
  const decrement = () => {
    // Đảm bảo rằng số lượng không thể nhỏ hơn 1
    if (props.quantity > 1) {
      // Cập nhật số lượng sản phẩm trong Redux store
      dispatch(cartActions.updateQuantity({ id: props.id, quantity: props.quantity - 1 }));
    }
  };
  return (
    <tr>
      <td>
        <img src={props.imgUrl} alt="product" />
      </td>
      <td
        className="product__title"
        onClick={() => {
          props.detail(props.id);
        }}
      >
        {props.productName}
      </td>
      <td>{priceFormat(props.price)}</td>

      <td>
        <div className="quantity__wrapper">
          <motion.button
            whileTap={{ scale: 0.9 }}
            className="quantity__btn"
            onClick={decrement}
          >
            <GrSubtract />
          </motion.button>
          <span className="quantity__number">{props.quantity}</span>
          <motion.button
            whileTap={{ scale: 1.1 }}
            className="quantity__btn"
            onClick={increment}
          >
            <FaPlus />
          </motion.button>
        </div>
      </td>

      <td>
        <motion.i
          whileHover={{ scale: 1.2, color: "#FF0033" }}
          onClick={() => props.delete(props.id)}
          className="ri-delete-bin-line fs-5"
        ></motion.i>
      </td>
    </tr>
  );
};

const Cart = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const cartItems: any = useSelector<TReducers>(
    (state) => state.cart.cartItems
  );

  const totalAmount: any = useSelector<TReducers>(
    (state) => state.cart.totalAmount
  );


  const totalQuantity: any = useSelector<TReducers>(
    (state) => state.cart.totalQuantity
  );

  const totalSale: any = useSelector<TReducers>((state)=> state.cart.totalSalesPrice)

  const total: any = useSelector<TReducers>((state)=> state.cart.totalFinal)

  const productDetail = (id: any) => {
    navigate(`/shop/${id}`);
  };

  const handleDelete = (id: any) => {
    dispatch(cartActions.deleteItem(id));
  };

  const handlePayment = () => {
    if (totalQuantity) {
      navigate("/home");
      setTimeout(() => {
        toast.success("Thanh toán thành công!", {
          position: toast.POSITION.TOP_CENTER
        });
      }, 500);
    } else {
      toast.warning("Product Cart null");
    }
  };
  return (
    <Helmet title="Cart">
      <CommonSection title="Shopping Cart" />
      <section>
        <Container>
          <Row>
            <Col lg="9">
              {cartItems.length === 0 ? (
                <h2 className="fs-4 text-center"> No Items Added the cart</h2>
              ) : (
                <table className="table boder">
                  <thead>
                    <tr>
                      <th>Image</th>
                      <th>Title</th>
                      <th>Price</th>
                      <th>Quantity</th>
                      <th>Delete</th>
                    </tr>
                  </thead>

                  <tbody>
                    {cartItems.map((item: any, index: number) => {
                      return (
                        <PropsCart
                          key={index}
                          id={item.id}
                          imgUrl={item.imgUrl}
                          price={item.price}
                          productName={item.productName}
                          quantity={item.quantity}
                          file={item.file}
                          detail={() => productDetail(item.id)}
                          delete={() => handleDelete(item.id)}
                        />
                      );
                    })}
                  </tbody>
                </table>
              )}
            </Col>
            <Col lg="3">
              <div>
                <h6 className="d-flex align-items-center justify-content-between ">
                  Subtotal
                  <span className="fs-5 fw-bold">
                    {priceFormat(totalAmount)}
                  </span>
                </h6>

                <h6 className="d-flex align-items-center justify-content-between ">
                  Discount
                  <span className="fs-5 fw-bold">
                    {totalSale}%
                  </span>
                </h6>

                
                <h6 className="d-flex align-items-center justify-content-between ">
                  Total
                  <span className="fs-4 fw-bold">
                    {priceFormat(total)}
                  </span>
                </h6>


              </div>
              <p className="fs-6 mt-2">
                Taxes and shipping will calculate in checkout
              </p>
              <div>
                <motion.button
                  whileTap={{ scale: 1.1 }}
                  className="buy__btn w-100"
                  onClick={handlePayment}
                >
                  Confirm Payment
                </motion.button>

                <motion.button
                  whileTap={{ scale: 1.1 }}
                  className="buy__btn w-100"
                  onClick={()=>navigate("/shop")}
                >
                  
                    Continue Shopping
                  
                </motion.button>
              </div>
            </Col>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Cart;
