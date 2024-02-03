import React from "react";

type userProps = {
  id:any,
  img: string;
  userName: string;
  email: string;
  phone: string;
  address: string;
  delete: (id:any) => void;
  edit: (id:any) => void;
};

const UserItem = (props: userProps) => {
  return (
    <tr>
      <td>
        <div
          className="w-40 h-40"
          style={{ border: "none", borderRadius: "50%" }}
        >
          <img style={{ objectFit: "cover" }} src={props.img} alt="" />
        </div>
      </td>
      <td>{props.userName}</td>
      <td>{props.email}</td>
      <td>{props.phone}</td>
      <td>{props.address}</td>
      <td>
        <button className="btn btn-danger m-1" onClick={()=>props.delete(props.id)}>Delete</button>
        <button className="btn btn-primary" onClick={()=>props.edit(props.id)}>Edit</button>
      </td>
    </tr>
  );
};

export default UserItem;
