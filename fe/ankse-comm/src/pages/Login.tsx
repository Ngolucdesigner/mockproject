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
import { useDispatch } from "react-redux";
import { login } from "../redux/slices/isLogin";

type loginInformation = loginInfo & {
  fullName: string;
};
const Login = () => {
  const navigate = useNavigate();
  const location = useLocation();


  const [username, setUsername] = useState<string>();

  const [password, setPassword] = useState<string>();
  const [loading, setLoading] = useState(false);

  const dispatch = useDispatch();

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
    setLoading(true);
    try {
      request
        .post1<loginInformation>(
          "auth/sign-in",
          { headers: config },
          JSON.stringify({ username, password })
        )
        .then((res) => {
          
          setResponseToCookie("user", res.data.token);

          dispatch(login.setLogin(res.data));
          dispatch(login.isLogin(true));
          location.pathname.startsWith("/dashboard") &&
          res.data.roles[0] === "ADMIN"
            ? navigate("/dashboard/dashboard")
            : navigate("/home");
            setLoading(false);
        })
        .catch(() => {
          setTimeout(() => {
            toast.error("Đăng nhập thất bại!", {
              position: toast.POSITION.TOP_CENTER,
            });
          }, 500);
          setLoading(false);
        });
    } catch (error) {
      return Promise.reject(error);
    }
  };

  
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
                      required
                    />
                  </FormGroup>

                  <FormGroup className="form__group">
                    <Input
                      type="password"
                      placeholder="enter your password"
                      value={password}
                      onChange={handleChangePassword}
                      required
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
