import React, { useState } from "react";
import Helmet from "../components/helmet/Helmet";
import { Col, Container, Row, Form, FormGroup, Input } from "reactstrap";
import { Link, useLocation, useNavigate } from "react-router-dom";

import Spinner from "react-bootstrap/Spinner";

import "../styles/Login.scss";
import { toast } from "react-toastify";

import * as request from "../Utils/request";
import { loginInfo } from "../model/login";
import { setResponseToCookie } from "../Utils/customCookie";


const Login = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const [loginInfo, setLoginInfo] = useState<loginInfo>();
  const [username, setUsername] = useState<string>();

  const [password, setPassword] = useState<string>();

  const handleChangeUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handleChangePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const config = {
    // withCredentials: true,
    "Content-Type": "application/json",
    // Authorization: "Basic " + localStorage.getItem("cookie"),
    // 'Access-Control-Allow-Origin': false ,
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    try {
      request
        .post1<loginInfo>(
          "auth/sign-in",
          { headers: config },
          JSON.stringify({ username, password })
        )
        .then((res) => {
        
        setLoginInfo(res.data)
        setResponseToCookie("user",res.data.token);    
      
        location.pathname.startsWith("/dashboard") && (res.data.roles[0]=== "ADMIN")  ?  navigate("/dashboard"): navigate("/home")
      
              
        })
        .catch(() => {
          setTimeout(() => {
            toast.error("Đăng nhập thất bại!", {
              position: toast.POSITION.TOP_CENTER,
            });
          }, 500);
        });
    } catch (error) {
      return Promise.reject(error);
    }
    

  };

  const loading = false;

  return (
    <Helmet title="Login">
      <section>
        <Container>
          <Row>
            {loading ? (
              <Col lg="12" className="text-center">
                <h5 className="d-flex align-items-center justify-content-center gap-2">
                  <Spinner animation="border" variant="info" />
                  <span>Loading...</span>
                </h5>
              </Col>
            ) : (
              <Col lg="6" className="m-auto text-center">
                <h3 className="fw-bold mb-4">Login</h3>
                <Form className="auth__form" onSubmit={handleSubmit}>
                  <FormGroup className="form__group">
                    <Input
                      type="text"
                      placeholder="enter your username"
                      value={username}
                      onChange={handleChangeUsername}
                    />
                  </FormGroup>

                  <FormGroup className="form__group">
                    <Input
                      type="password"
                      placeholder="enter your password"
                      value={password}
                      onChange={handleChangePassword}
                    />
                  </FormGroup>

                  <button type="submit" className="buy__btn auth__btn">
                    Login
                  </button>
                  <p>
                    Don't have account?{" "}
                    <Link to={"/signup"} className="link">
                      Create an account
                    </Link>
                  </p>
                </Form>
              </Col>
            )}
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Login;
