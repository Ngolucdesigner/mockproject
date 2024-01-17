import React, { useState } from "react";

import Helmet from "../components/helmet/Helmet";
import { Col, Container, Form, FormGroup, Input, Row } from "reactstrap";
import { Link } from "react-router-dom";

const Signup = () => {
  const [email, setEmail] = useState<string>();

  const [pass, setPass] = useState<string>();

  const [user, setUser] = useState<string>();

  const [img, setImg] = useState<any>();

  const handleChangeEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handleChangePass = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPass(event.target.value);
  };

  const handleChangeUser = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUser(event.target.value);
  };

  const handleChangeFile = (event: React.ChangeEvent<HTMLInputElement>) => {
    setImg(event.target.files);
  };
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    console.log({ email: email, pass: pass, user: user });
  };

  return (
    <Helmet title="Login">
      <section>
        <Container>
          <Row>
            <Col lg="6" className="m-auto text-center">
              <h3 className="fw-bold mb-4">Sign Up</h3>
              <Form className="auth__form" onSubmit={handleSubmit}>
                <FormGroup className="form__group">
                  <Input
                    type="text"
                    placeholder="Username"
                    value={user}
                    onChange={handleChangeUser}
                  />
                </FormGroup>

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

                <FormGroup className="form__group">
                  <Input type="file"  onChange={handleChangeFile} />
                </FormGroup>

                <button type="submit" className="buy__btn auth__btn">
                  Create an account
                </button>
                <p>
                  Already have an account?{" "}
                  <Link to={"/login"} className="link">
                    Login
                  </Link>
                </p>
              </Form>
            </Col>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Signup;
