import React, { useState } from "react";

import Helmet from "../components/helmet/Helmet";
import { Col, Container, Form, FormGroup, Input, Row } from "reactstrap";
import { Link, useNavigate } from "react-router-dom";

const Signup = () => {
  const navigate = useNavigate();

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
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e) => {
        // Sử dụng type assertion để khẳng định e.target không phải là null
        const target = e.target as FileReader;
        setImg(target.result);
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };
  

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData();
    if (username) formData.append('username', username);
    if (email) formData.append('email', email);
    if (password) formData.append('password', password);
    if (img && img[0]) formData.append('avatar', img[0]);


    try {
      const response = await fetch('http://localhost:8080/api/v1/signup', {
        method: 'POST',
        body: formData
      });

      const data = await response.json();

      if (response.ok) {
        const imageUrl = img && img[0] ? URL.createObjectURL(img[0]) : '';
        const formDataObject = {
          username: username,
          email: email,
          // Không lưu mật khẩu trong localStorage vì lý do bảo mật
          avatar: img && img[0] ? URL.createObjectURL(img[0]) : ''
        };
  
        localStorage.setItem('formData', JSON.stringify(formDataObject));
        localStorage.setItem('isLoggedIn', 'true');
        navigate("/home");
      } else {
        console.error('Đăng ký không thành công');
      }
    } catch (error) {
      console.error('Lỗi khi gửi yêu cầu đăng ký:', error);
    }
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
