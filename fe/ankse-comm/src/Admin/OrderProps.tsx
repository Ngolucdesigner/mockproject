import React from "react";
import { string } from "yup";
import { priceFormat } from "../model/FormatVND";

type productDetail = {
  id: any;
  productName: string;
  price: any;
  priceSales: any;
};
type orderDetail = {
  orderDetailsId: any;
  productQuantity: any;
  product: productDetail;
};

type order = {
  orderId: any;
  status: string;
  orderDate: string;
  totalPrice: any;
  orderDetails: orderDetail[];
};

type propsItem = {
  customersId: any;
  fullName: string;
  email?: string;
  phone: string;
  address: string;
  orders: order[];
  delete: (id:any)=>void
  detail: (id:any)=>void
};

const OrderProps = (props: propsItem) => {
  return (
    <tr>
      <td>{props.customersId}</td>
      <td>{props.fullName}</td>
      <td>{props.phone}</td>
      <td>{props.address}</td>
      <td>
        {props.orders.map((item, index) => (
          <ol key={index}>
            {item.orderDetails.map((i, ind) => (
              <li key={ind}>{i.product.productName}</li>
            ))}
          </ol>
          
        ))}
      </td>

      <td>{props.orders.map((item) => priceFormat(item.totalPrice))}</td>
      <td>{props.orders.map((item) => item.status)}</td>

      <td>
        <button className="btn btn-danger m-1" onClick={()=> props.delete(props.customersId)}>Delete</button>
        <button className="btn btn-primary m-1" onClick={()=> props.detail(props.customersId)}>Detail</button>
      </td>
    </tr>
  );
};

export default OrderProps;
