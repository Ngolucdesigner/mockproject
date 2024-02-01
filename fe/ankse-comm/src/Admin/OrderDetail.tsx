import React from 'react';
import { Col, Container, Row, Table } from 'reactstrap';
import Spinner from "react-bootstrap/Spinner";
const OrderDetail = () => {
    const [loading, setLoading] = useState(false);
    return (
        <section>
            <Container>
                <Row>
                    <Col>
                    
                    {loading ? (
              <h5 className="d-flex align-items-center justify-content-center  gap-2">
                <Spinner animation="border" variant="info" />
                <span>Loading...</span>
              </h5>
            ) : (
              <Table responsive>
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Customer</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  {/* {getOrder.map((item, index) => (
                    <OrderProps
                      key={index}
                      customersId={item.customersId}
                      fullName={item.fullName}
                      address={item.address}
                      phone={item.phone}
                      orders={item.orders}
                      delete={() => deleteOrder(item.customersId)}
                      detail={() => detail(item.customersId)}
                    />
                  ))} */}
                </tbody>
              </Ta>
            )}
                    </Col>
                </Row>
            </Container>
            
        </section>
    );
};

export default OrderDetail;