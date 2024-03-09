import { useState } from "react";
import { Button, Col, Container, Row } from "reactstrap";
import * as request from "../Utils/request";

import Spinner from "react-bootstrap/Spinner";
import UserItem from "./UserItem";
import { toast } from "react-toastify";
import { useDispatch } from "react-redux";

import { getDataFromCookie } from "../Utils/customCookie";
import useGetAccount from "../custom-hooks/useGetAccount";
import { useNavigate } from "react-router-dom";
import { reloadAccount } from "../redux/slices/loadAccount";
const User = () => {
  const [loading, setLoading] = useState(false);

  const dataUser = useGetAccount();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const config = {
    // withCredentials: true,
    "Content-Type": "application/auto",
    // 'Cookie': `anks=${getDataFromCookie("user")}`
    Authorization: `Bearer ${getDataFromCookie("user")}`,
    // 'Access-Control-Allow-Origin': false ,
  };

  const reload = () => {
    dispatch(reloadAccount.reloadAccount(true));
  };

  const handleDelete = (id: any) => {
    setLoading(true);
    const path = `accounts/delete/${id}`;
    try {
      request
        .delete1(path, { headers: config })
        .then((res) => {
          
          toast.success("Delete successfully");
          setLoading(false);
          reload();
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
  const handleCreateAccount = () => {
    navigate("/dashboard/dashboard/users/create");
  };

  
  const handleEdit = (id: any) => {
    navigate(`/dashboard/dashboard/users/edit/${id}`)
  };

  
  return (
    <section>
      <Container>
        <Row>
          <Col lg="12 d-flex align-item-center justify-content-between">
            <h4 className="fw-bold">Users</h4>
            <div className="user-menu d-flex gap-3">
              <Button onClick={handleCreateAccount}>
                <i className="ri-user-add-fill"></i>{" "}
              </Button>
              <Button>
                <i className="ri-filter-line"></i>
              </Button>
            </div>
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
