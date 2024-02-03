import React, { useState } from "react";
import { Col, Container, Row } from "reactstrap";
import * as request from "../Utils/request";

import useGetData from "../custom-hooks/useGetData";
import Spinner from "react-bootstrap/Spinner";
import UserItem from "./UserItem";
import { toast } from "react-toastify";
import { useDispatch } from "react-redux";
import { reloadProduct } from "../redux/slices/loadProduct";
const User = () => {
  const [loading, setLoading] = useState(false);

  const dataUser = useGetData();
  const dispatch = useDispatch();
  const config = {
    // withCredentials: true,
    "Content-Type": "application/auto",
    // Authorization: "Basic " + localStorage.getItem("cookie"),
    // 'Access-Control-Allow-Origin': false ,
  };
  
  const reload = () => {
    dispatch(reloadProduct.reloadProduct(true));
  };


  const handleDelete = (id: any) => {
    setLoading(true);
    const path = "accounts/delete/" + id;
    try {
      request
        .delete1(path, { headers: config })
        .then((res) => {
          reload();
          toast.success("Delete successfully");
          setLoading(false);
        })
        .catch((error) => {
          console.error(error);
          toast.error("Delete False");
          setLoading(false);
        });
    } catch (error) {
      console.error(error);
    }

  };
  const handleEdit = (id: any) => {};
  return (
    <section>
      <Container>
        <Row>
          <Col lg="12">
            <h4 className="fw-bold">Users</h4>
          </Col>
          <Col lg="12" className="pt-5">
            <table className="table">
              <thead>
                <tr>
                  <td>Image</td>
                  <td>Username</td>
                  <td>Email</td>
                  <td>Phone</td>
                  <td>Address</td>
                  <td>Action</td>
                </tr>
              </thead>

              {loading ? (
                <h5 className="w-100 d-flex align-items-center justify-content-center  gap-2 mt-5">
                  <Spinner animation="border" variant="info" />
                  <span>Loading...</span>
                </h5>
              ) : (
                <tbody>
                  {dataUser.useData.map((item: any, index) => (
                    <UserItem
                      key={index}
                      id={item.id}
                      userName={item.username}
                      email={item.email}
                      address={item.address}
                      img={item.file?.url}
                      phone={item.phone}
                      delete={() => handleDelete(item.id)}
                      edit={() => handleEdit(item.id)}
                    />
                  ))}
                </tbody>
              )}
            </table>
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default User;
