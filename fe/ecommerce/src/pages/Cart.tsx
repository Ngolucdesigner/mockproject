import React, { useState } from 'react';
import { Container, Row, Col, ListGroup, ListGroupItem, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import products from '../assets/data/products';
import { FaTrash, FaMinus, FaPlus } from 'react-icons/fa';
import { CiTextAlignCenter } from 'react-icons/ci';
import "../styles/Cart.scss";
type CartItem = {
    id: string;
    name: string;
    price: number;
    quantity: number;
    imgUrl: string;
};

// Example of initial cart items with quantities
const initialCartItems: CartItem[] = products.map(product => ({
    id: product.id,
    name: product.productName,
    price: product.price,
    quantity: 1,
    imgUrl: product.imgUrl,
}));

;

const Cart = () => {
    const [cartItems, setCartItems] = useState<CartItem[]>(initialCartItems);

    const incrementQuantity = (id: string) => {
        const newCartItems = cartItems.map(item => {
            if (
                item.id === id) {
                return { ...item, quantity: item.quantity + 1 };
            }
            return item;
        });
        setCartItems(newCartItems);
    };

    // Function to handle quantity decrement
    const decrementQuantity = (id: string) => {
        const newCartItems = cartItems.map(item => {
            if (item.id === id && item.quantity > 1) {
                return { ...item, quantity: item.quantity - 1 };
            }
            return item;
        });
        setCartItems(newCartItems);
    };

    // Function to handle item removal
    const removeItem = (id: string) => {
        const newCartItems = cartItems.filter(item => item.id !== id);
        setCartItems(newCartItems);
    };

    const subtotal = cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
    const shippingFee = 53000;
    const total = subtotal + shippingFee;

    return (
        <Container>
        <Row>
            <Col md="8">
                <ListGroup>
                    {cartItems.map((item) => (
                        <ListGroupItem key={item.id}>
                            <Row>
                                <Col xs="2" className='align-items-center d-flex'>
                                    <img src={item.imgUrl} alt={item.name} className="img-fluid" />
                                </Col>
                                <Col xs="4" className='align-items-center d-flex'>
                                    <h5>{item.name}</h5>
                                </Col>
                                <Col xs="2" className='align-items-center d-flex'>
                                    {item.price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}
                                </Col>
                                <Col xs="4" className="d-flex align-items-center">
                                    <Button className="quantity-btn" onClick={() => decrementQuantity(item.id)}>
                                        <FaMinus />
                                    </Button>
                                    <Input type="number" value={item.quantity} readOnly className="quantity-input"/>
                                    <Button className="quantity-btn" onClick={() => incrementQuantity(item.id)}>
                                        <FaPlus />
                                    </Button>
                                    <Button className="btn-danger" onClick={() => removeItem(item.id)}>
                                        <FaTrash />
                                    </Button>
                                </Col>
                            </Row>
                        </ListGroupItem>
                    ))}
                </ListGroup>
            </Col>
            <Col md="4">
                    <h4>Order Summary</h4>
                    <ListGroup>
                        <ListGroupItem>
                            <Row>
                                <Col xs="6">Subtotal</Col>
                                <Col xs="6" className="text-right">{subtotal.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</Col>
                            </Row>
                        </ListGroupItem>
                        <ListGroupItem>
                            <Row>
                                <Col xs="6">Shipping Fee</Col>
                                <Col xs="6" className="text-right">{shippingFee.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</Col>
                            </Row>
                        </ListGroupItem>
                        <ListGroupItem>
                            <Row>
                                <Col xs="6"><strong>Total</strong></Col>
                                <Col xs="6" className="text-right"><strong>{total.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</strong></Col>
                            </Row>
                        </
                        ListGroupItem>
                    </ListGroup>
                    <Button color="primary" block>Confirm Cart</Button>
                </Col>
        </Row>
    </Container>
    );
};

export default Cart;