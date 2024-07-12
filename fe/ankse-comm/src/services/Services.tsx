import React, { useEffect, useState } from "react";

import { Container, Row, Col } from "reactstrap";
import { motion } from "framer-motion";

import "./Services.scss";

import serviceData from "../assets/data/serviceData";
import { servicesSetting } from "../model/services";

import * as request from "../Utils/request";

const Services = () => {
  const config = {
    withCredentials: true,
    "Content-Type": "application/json",

    // Cookie: Object.entries(cookies).map(([key, value]) => `${key}=${value}`).join('; ')
    // 'Access-Control-Allow-Origin': false ,
  };

  const [dataServicesSetting, setDataServicesSetting] =
    useState<servicesSetting>();

  // console.log(dataServicesSetting)
  const getSettingServices = async () => {
    try {
      await request
        .get1<servicesSetting>("ServicesSetting/setting", { headers: config })
        .then((res) => {
          setDataServicesSetting(res);
        })
        .catch((err) => {
          console.error(err);
        });
    } catch (error) {}
  };

  useEffect(() => {
    getSettingServices();
  }, []);

  return (
    <section className="services">
      <Container>
        <Row>
          {dataServicesSetting
            ? dataServicesSetting.map((item, index) => (
                <Col lg="3" md="4" key={index}>
                  <motion.div
                    whileHover={{ scale: 1.1 }}
                    className="service__item"
                    style={{ background: `${item.background}` }}
                  >
                    <span>
                      <i className={item.icon}></i>
                    </span>
                    <div>
                      <h3>{item.title}</h3>
                      <p>{item.subTitle}</p>
                    </div>
                  </motion.div>
                </Col>
              ))
            : serviceData.map((item, index) => (
                <Col lg="3" md="4" key={index}>
                  <motion.div
                    whileHover={{ scale: 1.1 }}
                    className="service__item"
                    style={{ background: `${item.bg}` }}
                  >
                    <span>
                      <i className={item.icon}></i>
                    </span>
                    <div>
                      <h3>{item.title}</h3>
                      <p>{item.subtitle}</p>
                    </div>
                  </motion.div>
                </Col>
              ))}
        </Row>
      </Container>
    </section>
  );
};

export default Services;
