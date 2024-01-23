import React from 'react';
import { Col, Container, Row, Table } from 'reactstrap';

const Order = () => {
    return (
      <section>
        <Container>
            <Row>
                <Col >
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Customer</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
      </section>
    );
};

export default Order;