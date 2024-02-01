import React, { useEffect, useState } from "react";
import { Col, Container, Row, Table } from "reactstrap";
import OrderProps from "./OrderProps";

import * as request from "../Utils/request";
import { toast } from "react-toastify";
import Spinner from "react-bootstrap/Spinner";
import { useDispatch } from "react-redux";
import { quantity } from "../redux/slices/order";

type productDetail = {
  id: any;
  productName: string;
  price: any;
  priceSales: any;
};
type orderDetail = {
  orderDetailsId: any;
  productQuantity: any;
  product: productDetail;
};

type order = {
  orderId: any;
  status: string;
  orderDate: string;
  totalPrice: any;
  orderDetails: orderDetail[];
};

type propsItem = {
  customersId: any;
  fullName: string;
  email: string;
  phone: string;
  address: string;
  orders: order[];
};

const Order = () => {
  const [getOrder, setGetOrder] = useState<Array<propsItem>>([]);
  const [reload, setReload] = useState(false);

  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();

  const [quantityOrder, setQuantityOrder] = useState();
  const [salesOrder, setSalesOrder] = useState();

  const config = {
    // withCredentials: true,
    "Content-Type": "application/json",
    // Authorization: "Basic " + localStorage.getItem("cookie"),
    // 'Access-Control-Allow-Origin': false ,
  };

  const getAllOrder = async () => {
    try {
      setLoading(true);
      await request
        .get("customer/all-customer", { headers: config })
        .then((res) => {
          setGetOrder(res.content);
          setQuantityOrder(res.totalElements);
          setReload(false);
          setLoading(false);
        })
        .catch((error) => {
          console.log(error);
          setReload(false);
          setLoading(false);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const deleteOrder = (id: any) => {
    try {
      setLoading(true);
      request
        .delete1(`customer/delete/${id}`, { headers: config })
        .then((res) => {
          toast.success("Delete successfully");

          setReload(true);
          setLoading(false);
        })
        .catch((error) => {
          toast.error("Delete False");
          console.log(error);
          setLoading(false);
        });
    } catch (error) {
      console.error(error);
    }
  };

  let totalPrice = 0;
  getOrder.map((item) => {
    totalPrice += item.orders[0].totalPrice;
  });

  dispatch(quantity.quantityOrder(quantityOrder));
  dispatch(quantity.quantitySales(totalPrice));

  const detail = (id: any) => {};

  useEffect(() => {
    getAllOrder();
  }, [reload]);

  return (
    <section>
      <Container>
        <Row>
          <Col>
            {loading ? (
              <h5 className="d-flex align-items-center justify-content-center  gap-2">
                <Spinner animation="border" variant="info" />
                <span>Loading...</span>
              </h5>
            ) : (
              <Table responsive>
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Customer</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  {getOrder.map((item, index) => (
                    <OrderProps
                      key={index}
                      customersId={item.customersId}
                      fullName={item.fullName}
                      address={item.address}
                      phone={item.phone}
                      orders={item.orders}
                      delete={() => deleteOrder(item.customersId)}
                      detail={() => detail(item.customersId)}
                    />
                  ))}
                </tbody>
              </Table>
            )}
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default Order;
