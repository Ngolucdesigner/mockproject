import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import {
  
  Button,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
  Spinner,
} from "reactstrap";

import { getDataFromCookie } from "../Utils/customCookie";
import { useDispatch } from "react-redux";

import * as request from "../Utils/request";
import { toast } from "react-toastify";
import { reloadAccount } from "../redux/slices/loadAccount";

import { userPops } from "../model/user";

type userProps = {
  data: any;
};

const UserDetail = () => {
  const location = useLocation();
  const dispatch = useDispatch();
  const { id } = useParams();

  const [username, setUsername] = useState<string>("");
  const [lastName, setLastName] = useState<string>("");
  const [firstName, setFirstName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [phone, setPhone] = useState<number>(0);
  const [role, setRoles] = useState<string>("CUSTOMER");
  const [password, setPassWord] = useState<string>("");
  const [avatar, setAvatar] = useState<any>();

  const [user, setUser] = useState<userPops>();

  const [loading, setLoading] = useState<boolean>(false);

  const handleChangeUserName = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };
  const handleChangeEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };
  const handleChangeFirstName = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setFirstName(event.target.value);
  };
  const handleChangeLastName = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLastName(event.target.value);
  };
  const handleChangeAddress = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAddress(event.target.value);
  };
  const handleChangePhone = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPhone(Number(event.target.value));
  };

  const handleChangeRoles = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRoles(event.target.value);
  };
  const handleChangePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassWord(event.target.value);
  };
  const handleChangeAvatar = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAvatar(event.target.files?.[0]);
  };

  const reload = () => {
    dispatch(reloadAccount.reloadAccount(true));
  };

  const config = {
    // withCredentials: true,
    "Content-Type": "application/auto",
    Authorization: `Bearer ${getDataFromCookie("user")}`,
    // 'Cookie': "anks="+getDataFromCookie("user").toString()
    // Authorization: "Basic " + localStorage.getItem("cookie"),
    // 'Access-Control-Allow-Origin': false ,
  };

  const handleSubmit = (event: React.ChangeEvent<HTMLFormElement>) => {
    event.preventDefault();
    const formData = new FormData();

    formData.append("username", username);
    formData.append("email", email);
    formData.append("firstName", firstName);
    formData.append("lastName", lastName);
    formData.append("address", address);
    formData.append("phone", phone.toString());
    formData.append("password", password);
    formData.append("role", role);
    formData.append("avatar", avatar as File);
    setLoading(true);
    if (id) {
      try {
        request
          .put1<ResponseType>(
            `accounts/update-account/${id}`,
            { headers: config },
            formData
          )
          .then(() => {
            toast.success("Account update successfully");
            setLoading(false);
          })
          .catch((err) => {
            setLoading(false);
            toast.error("Account update fail");
            console.error(err);
          });
      } catch (err) {
        console.error(err);
      }
    } else {
      try {
        request
          .post1<ResponseType>("create-account", { headers: config }, formData)
          .then((res) => {
            toast.success("Account create successfully");
            reload();
            setLoading(false);
          })
          .catch(() => {
            setLoading(false);
          });
      } catch (error) {}
    }
  };

  const getAccountById = async () => {
    try {
      await request
        .get1<userPops>(`accounts/${id}`, { headers: config })
        .then((res) => {
          
          setUser(res);
        });
    } catch (error) {}
  };

  useEffect(() => {
    if (id) {
      getAccountById();
    }
  }, []);

  useEffect(() => {
    if (user) {
      setUsername(user.username);
      setEmail(user.email);
      setAddress(user.address);
      setFirstName(user.firstName);
      setLastName(user.lastName);
      setPhone(Number(user.phone));
      if (user.role) setRoles(user.role);
    }
  }, [user]);

  const handleReloadActive = (email?: string) => {
    const data = new FormData();
    if (email) data.append("email", email);

    if (!user?.active) {
      try {
        request
          .put1<ResponseType>("regenerate-otp", { headers: config }, data)
          .then(() => {
            toast.success("Regenerate OTP OK");
          })
          .catch(() => {
            toast.error("Error");
          });
      } catch (error) {}
    }
  };

  return (
    <section>
      <Container>
        <Row>
          {loading ? (
            <h5 className="d-flex align-items-center justify-content-center  gap-2">
              <Spinner animation="border" variant="info" />
              <span>Loading...</span>
            </h5>
          ) : (
            <Col lg="12">
              <div className="d-flex align-items-center justify-content-between">
                <h3>Account</h3>
                {location.pathname.startsWith(
                  "/dashboard/dashboard/users/edit"
                ) ? (
                  <div className="">
                    <Button
                      color={user?.active ? "success" : "danger"}
                      onClick={() => handleReloadActive(user?.email)}
                    >
                      {user?.active ? (
                        <i className="ri-check-line"></i>
                      ) : (
                        <i className="ri-refresh-line"></i>
                      )}
                    </Button>
                  </div>
                ) : null}
              </div>
              <Form onSubmit={handleSubmit}>
                <Row>
                  <Col md={6}>
                    <FormGroup>
                      <Label for="exampleUserName">Username</Label>
                      <Input
                        name="username"
                        placeholder="Enter your username!"
                        type="text"
                        value={username}
                        onChange={handleChangeUserName}
                        required
                      />
                    </FormGroup>
                  </Col>
                  <Col md={6}>
                    <FormGroup>
                      <Label for="exampleEmail">Email</Label>
                      <Input
                        name="email"
                        placeholder="Enter your email!"
                        type="email"
                        value={email}
                        onChange={handleChangeEmail}
                        required
                      />
                    </FormGroup>
                  </Col>
                </Row>

                <Row>
                  <Col md={6}>
                    <FormGroup>
                      <Label for="exampleFirtName">First name</Label>
                      <Input
                        name="firstName"
                        placeholder="Enter your first name!"
                        type="text"
                        value={firstName}
                        onChange={handleChangeFirstName}
                        required
                      />
                    </FormGroup>
                  </Col>
                  <Col md={6}>
                    <FormGroup>
                      <Label for="exampleLastName">Last name</Label>
                      <Input
                        name="lastName"
                        placeholder="Enter your last name!"
                        type="text"
                        value={lastName}
                        onChange={handleChangeLastName}
                        required
                      />
                    </FormGroup>
                  </Col>
                </Row>

                <Row>
                  <Col md={6}>
                    <FormGroup>
                      <Label for="exampleAddress">Address</Label>
                      <Input
                        name="address"
                        placeholder="Enter your address!"
                        type="text"
                        value={address}
                        onChange={handleChangeAddress}
                        required
                      />
                    </FormGroup>
                  </Col>
                  <Col md={6}>
                    <FormGroup>
                      <Label for="examplePhone">Phone number</Label>
                      <Input
                        name="phoneNumber"
                        placeholder="Enter your phone number!"
                        type="number"
                        value={phone}
                        onChange={handleChangePhone}
                        required
                      />
                    </FormGroup>
                  </Col>
                </Row>

                <Row>
                  <Col>
                    <FormGroup>
                      <Label for="exampleRoles">Roles</Label>
                      <Input
                        name="select"
                        type="select"
                        value={role}
                        onChange={handleChangeRoles}
                      >
                        <option>CUSTOMER</option>
                        <option>ADMIN</option>
                        <option>OTHER</option>
                      </Input>
                    </FormGroup>
                  </Col>
                  <Col>
                    <FormGroup>
                      <Label for="exampleAvatar">Avatar</Label>
                      <Input type="file" onChange={handleChangeAvatar} />
                    </FormGroup>
                  </Col>
                </Row>
                <FormGroup>
                  <Label for="examplePassword">Password</Label>
                  <Input
                    name="password"
                    type="password"
                    value={password}
                    onChange={handleChangePassword}
                  />
                </FormGroup>

                <Button type="submit">
                  {location.pathname.startsWith(
                    "/dashboard/dashboard/users/edit"
                  )
                    ? "Edit Account"
                    : "Create Account"}
                </Button>
              </Form>
            </Col>
          )}
        </Row>
      </Container>
    </section>
  );
};

export default UserDetail;
