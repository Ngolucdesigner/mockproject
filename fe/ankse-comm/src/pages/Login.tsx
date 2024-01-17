import React, { useState } from "react";
import Helmet from "../components/helmet/Helmet";
import { Col, Container, Row, Form, FormGroup, Input } from "reactstrap";
import { Link } from "react-router-dom";

import Spinner from "react-bootstrap/Spinner";

import "../styles/Login.scss";
const Login = () => {
  const [email, setEmail] = useState<string>();

  const [pass, setPass] = useState<string>();

  const handleChangeEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handleChangePass = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPass(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    console.log({ email: email, pass: pass });
  };

  const loading = true;

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
                      type="email"
                      placeholder="enter your email"
                      value={email}
                      onChange={handleChangeEmail}
                    />
                  </FormGroup>

                  <FormGroup className="form__group">
                    <Input
                      type="password"
                      placeholder="enter your password"
                      value={pass}
                      onChange={handleChangePass}
                    />
                  </FormGroup>

                  <button type="submit" className="buy__btn auth__btn">
                    Login
                  </button>
                  <p>
                    Don' t have account?{" "}
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
