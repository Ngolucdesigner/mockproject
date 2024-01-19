// import axios from "axios";
import React, { useState } from "react";
import { toast } from "react-toastify";
import { Col, Container, Form, FormGroup, Input, Label, Row } from "reactstrap";
import Spinner from "react-bootstrap/Spinner";

import * as request from "../Utils/request";
import { categoryList } from "../model/categoryData";

import "../styles/AddProduct.scss";

const options = categoryList.slice(1);

const AddProducts = () => {


  const [title, setTitle] = useState("");
  const [shortDescription, setShortDescription] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [priceSales, setPriceSales] = useState("");
  const [category, setCategory] = useState("");
  const [productImg, setProductImg] = useState<any>();

  const [manufacturer, setManufacturer] = useState("");
  const [madeIn, setMadeIn] = useState("");
  const [guarantee, setGuarantee] = useState("");

  const [loading, setLoading] = useState(false);

  const handleChangeTitle = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  };

  const handleChangeShortDescription = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setShortDescription(event.target.value);
  };

  const handleChangeDescription = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setDescription(event.target.value);
  };

  const handleChangePrice = (event: React.ChangeEvent<HTMLInputElement>) => {
    Number(event.target.value) < 0
      ? setPrice("0")
      : setPrice(event.target.value);
  };

  const handleChangePriceSales = (event: React.ChangeEvent<HTMLInputElement>) => {
    Number(event.target.value) < 0
      ? setPriceSales("0")
      : setPriceSales(event.target.value);
  };
  

  const handleChangeCategory = (
    event: React.ChangeEvent<HTMLSelectElement>
  ) => {
    setCategory(event.target.value);
  };
  const handleChangeImg = (event: React.ChangeEvent<HTMLInputElement>) => {
    setProductImg(event.target.files?.[0]);
  };

  const handleChangeManufacturer = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setManufacturer(event.target.value);
  };

  const handleMadeIn = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMadeIn(event.target.value);
  };

  const handleGuarantee = (event: React.ChangeEvent<HTMLInputElement>) => {
    setGuarantee(event.target.value);
  };

  const config = {
    // withCredentials: true,
    "Content-Type": "application/auto",
    // Authorization: "Basic " + localStorage.getItem("cookie"),
    // 'Access-Control-Allow-Origin': false ,
  };

  const handleSubmit = (event: React.ChangeEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append("productName", title);
    formData.append("shortDesc", shortDescription);
    formData.append("description", description);
    formData.append("price", price);
    formData.append("sales",priceSales);
    formData.append("category", category);
    formData.append("file", productImg as File);
    formData.append("manufacturer",manufacturer);
    formData.append("madeIn",madeIn);
    formData.append("guarantee",guarantee);
    setLoading(true);
    try {
      request
        .post1<ResponseType>(
          "products/new-products",
          { headers: config },
          formData
        )
        .then((response) => {
          // Handle the successful response here

          toast.success("Product successfully added");
          setLoading(false);
        })
        .catch((error) => {
          setLoading(false);
          // Handle any errors that occurred during the request
          toast.error("Product fail added!");
        });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <section>
      <Container>
        <Row>
          <Col lg="12">
            {loading ? (
              <h5 className="d-flex align-items-center justify-content-center  gap-2">
                <Spinner animation="border" variant="info" />
                <span>Loading...</span>
              </h5>
            ) : (
              <>
                <h4 className="mb-5">Add products</h4>
                <Form onSubmit={handleSubmit}>
                  <div className="container__product">
                    <FormGroup className="form__group">
                      <span>Product Name</span>
                      <Input
                        type="text"
                        placeholder="Product name"
                        value={title}
                        onChange={handleChangeTitle}
                        required
                      />
                    </FormGroup>
                    <FormGroup className="form__group">
                      <span>Short description</span>

                      <Col>
                        <Input
                          name="text"
                          type="textarea"
                          placeholder="Short Desc..."
                          value={shortDescription}
                          onChange={handleChangeShortDescription}
                          required
                        />
                      </Col>
                    </FormGroup>

                    <FormGroup className="form__group">
                      <span>Description</span>

                      <Col>
                        <Input
                          name="text"
                          type="textarea"
                          placeholder="description..."
                          value={description}
                          onChange={handleChangeDescription}
                          required
                        />
                      </Col>
                    </FormGroup>

                    <div className="d-flex align-items-center justify-content-between gap-5">
                      <FormGroup className="form__group w-50">
                        <span>Price</span>
                        <Input
                          type="number"
                          placeholder="VND"
                          value={price}
                          onChange={handleChangePrice}
                          required
                        />
                      </FormGroup>

                      <FormGroup className="form__group w-50">
                        <span>Sales</span>
                        <Input
                          type="number"
                          placeholder="%"
                          value={priceSales}
                          onChange={handleChangePriceSales}
                          required
                        />
                      </FormGroup>

                      <FormGroup className="form__group w-50">
                        <span>Category</span>
                        <select
                          className="w-100 p-2"
                          value={category}
                          onChange={handleChangeCategory}
                          required
                        >
                          {options.map((item, index) => (
                            <option key={index} value={item.value}>
                              {item.option}
                            </option>
                          ))}
                        </select>
                      </FormGroup>
                    </div>

                    <div>
                      <FormGroup className="form__group">
                        <span>Product Image</span>
                        <Input
                          type="file"
                          onChange={handleChangeImg}
                          required
                        />
                      </FormGroup>
                    </div>
                  </div>
                  <div className="product__info-container">
                    <h5>Thông tin Sản phẩm</h5>

                    <FormGroup row>
                      <Label sm={2}>Manufacturer</Label>
                      <Col>
                        <Input
                          placeholder="Thương hiệu"
                          type="text"
                          value={manufacturer}
                          onChange={handleChangeManufacturer}
                          required
                        />
                      </Col>
                    </FormGroup>

                    <FormGroup row>
                      <Label sm={2}>Made In</Label>
                      <Col>
                        <Input
                          placeholder="Nơi sản xuất"
                          type="text"
                          value={madeIn}
                          onChange={handleMadeIn}
                          required
                        />
                      </Col>
                    </FormGroup>

                    <FormGroup row>
                      <Label sm={2}>Guarantee</Label>
                      <Col>
                        <Input
                          placeholder="Bảo hành"
                          type="text"
                          value={guarantee}
                          onChange={handleGuarantee}
                          required
                        />
                      </Col>
                    </FormGroup>
                  </div>

                  <button className="buy__btn ">Add product</button>
                </Form>
              </>
            )}
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default AddProducts;
