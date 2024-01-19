import React, { useState } from "react";

import Helmet from "../components/helmet/Helmet";
import { Col, Container, Form, FormGroup, Input, Row } from "reactstrap";
import { Link } from "react-router-dom";

const Signup = () => {
  const [email, setEmail] = useState<string>();

  const [password, setPassword] = useState<string>();

  const [username, setUsername] = useState<string>();

  const [img, setImg] = useState<any>();

  const handleChangeEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handleChangePass = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleChangeUser = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handleChangeFile = (event: React.ChangeEvent<HTMLInputElement>) => {
    setImg(event.target.files);
  };
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData();
    if (username) formData.append('username', username);
    if (email) formData.append('email', email);
    if (password) formData.append('password', password);
    if (img && img[0]) formData.append('avatar', img[0]);


    const response = await fetch('http://localhost:8080/api/v1/accounts/new-account', {
      method: 'POST',
      body: formData
    });
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
                    value={username}
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
                    value={password}
                    onChange={handleChangePass}
                  />
                </FormGroup>

                <FormGroup className="form__group">
                  <Input type="file" onChange={handleChangeFile} />
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
