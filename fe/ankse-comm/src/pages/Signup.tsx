import React, { useState } from "react";

import Helmet from "../components/helmet/Helmet";
import { Col, Container, Form, FormGroup, Input, Row } from "reactstrap";
import { Link, useNavigate } from "react-router-dom";
import Spinner from "react-bootstrap/Spinner";
import * as request from "../Utils/request";
import { toast } from "react-toastify";

const Signup = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState<string>("");

  const [password, setPassword] = useState<string>("");

  const [username, setUsername] = useState<string>("");
  const [firstName, setFirstName] = useState<string>("");
  const [lastName, setLastName] = useState<string>("");

  const [loading, setLoading] = useState(false);

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

  const handleChangeFirstName = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setFirstName(event.target.value);
  };

  const handleChangeLastName = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLastName(event.target.value);
  };

  const handleChangeFile = (event: React.ChangeEvent<HTMLInputElement>) => {
    // if (event.target.files && event.target.files[0]) {
    //   const reader = new FileReader();
    //   reader.onload = (e) => {
    //     // Sử dụng type assertion để khẳng định e.target không phải là null
    //     const target = e.target as FileReader;
    //     setImg(target.result);
    //   };
    //   reader.readAsDataURL(event.target.files[0]);
    // }
    setImg(event.target.files?.[0]);
  };

  const config = {
    // withCredentials: true,
    "Content-Type": "application/auto",
    // Authorization: "Bearer " + getDataFromCookie("user"),
    // Authorization: "Basic " + localStorage.getItem("cookie"),
    // 'Access-Control-Allow-Origin': false ,
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    setLoading(true);
    const formData = new FormData();
    formData.append("username", username);
    formData.append("email", email);
    formData.append("password", password);
    formData.append("firstName", firstName);
    formData.append("lastName", lastName);
    formData.append("avatar", img as File);

    try {
      request
        .post1("register", { headers: config }, formData)
        .then(() => {
          toast.success("create account successfully");
          setLoading(false);
          navigate("/login");
        })
        .catch((err) => {
          console.log(err);
          setLoading(false);
        });
    } catch (error) {
      console.error("Lỗi khi gửi yêu cầu đăng ký:");
      setLoading(false);
    }
  };

  return (
    <Helmet title="sign-up">
      {loading ? (
        <div className="w-100 h-100">
          <h5 className=" d-flex align-items-center justify-content-center  gap-2">
            <Spinner animation="border" variant="info" />
            <span>Loading...</span>
          </h5>
        </div>
      ) : (
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
                      required
                    />

                  </FormGroup>
                  <div className="d-flex align-items-center justify-content-between gap-2">
                    <FormGroup className="form__group w-50">
                      <Input
                        type="text"
                        placeholder="First Name"
                        value={firstName}
                        onChange={handleChangeFirstName}
                        required
                      />
                    </FormGroup>

                    <FormGroup className="form__group w-50">
                      <Input
                        type="text"
                        placeholder="Last Name"
                        value={lastName}
                        onChange={handleChangeLastName}
                        required
                      />
                    </FormGroup>
                  </div>

                  <FormGroup className="form__group">
                    <Input
                      type="email"
                      placeholder="enter your email"
                      value={email}
                      onChange={handleChangeEmail}
                      required
                    />
                  </FormGroup>

                  <FormGroup className="form__group">
                    <Input
                      type="password"
                      placeholder="enter your password"
                      value={password}
                      onChange={handleChangePass}
                      required
                    />
                  </FormGroup>

                  <FormGroup className="form__group">
                    <Input type="file" onChange={handleChangeFile} required />
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
      )}
    </Helmet>
  );
};

export default Signup;
