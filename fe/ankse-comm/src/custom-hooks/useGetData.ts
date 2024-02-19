import  { useEffect, useState } from "react";
import { userPops } from "../model/user";
import * as request from "../Utils/request";
import { ProductProps } from "../model/productProps";

import { useDispatch, useSelector } from "react-redux";
import { TReducers } from "../redux/rootReducer";
import { reloadProduct } from "../redux/slices/loadProduct";
import { getDataFromCookie } from "../Utils/customCookie";


const useGetData = () => {
  const [useData, setUseData] = useState<Array<userPops>>([]);

  const [products, setProducts] = useState<Array<ProductProps>>([]);
  const [totalPagesProduct, setTotalPagesProduct] = useState<string>("");
  const [totalElementsProduct, setTotalElementsProduct] = useState("");
  const [totalPagesAccount, setTotalPagesAccount] = useState("");
  const [totalElementsAccount, setTotalElementsAccount] = useState("");

type res={
  content: any,
  totalPages:any,
  totalElements:any
}

  const notReload = () => {
    dispatch(reloadProduct.reloadProduct(false));
  };


  const reload = useSelector((state: TReducers) => state.reload.load);

  const dispatch = useDispatch();


  const page = useSelector((state:TReducers)=>state.changePage.page)


  const config = {
    withCredentials: true,
    "Content-Type": "application/json",
    Authorization: "Bearer " + getDataFromCookie("user")
    // 'Access-Control-Allow-Origin': false ,
  };

  const getAllAccount = async () => {
    try {

     await request
      .get1<res>("accounts", {headers: config})
      .then((res) => {
        setUseData(res.content);
        setTotalPagesAccount(res.totalPages);
        setTotalElementsAccount(res.totalElements);
        notReload();
      })
      .catch((err) => {
        console.error(err);
      });

    } catch (error) {
      
    }

  };

  const getAllProducts = async (page?: number|1) => {
    await request
      .get(`products?page=${page}`, { headers: config })
      .then((res) => {
        setProducts(res.content);
        setTotalPagesProduct(res.totalPages);
        setTotalElementsProduct(res.totalElements);
        notReload();
      })
      .catch((err) => {
         console.error(err);
      });
  };

  useEffect(() => {
    getAllAccount();
    getAllProducts(Number(page));
  }, [reload]);

  // useEffect(() => {
  //   getAllAccount();
  //   getAllProducts();
  // }, [reload]);

  return {
    useData,
    products,
    totalElementsAccount,
    totalElementsProduct,
    totalPagesAccount,
    totalPagesProduct,
  };
};

export default useGetData;
