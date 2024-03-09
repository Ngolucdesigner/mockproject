import  { useEffect, useState } from "react";

import * as request from "../Utils/request";
import { ProductProps } from "../model/productProps";

import { useDispatch, useSelector } from "react-redux";
import { TReducers } from "../redux/rootReducer";
import { reloadProduct } from "../redux/slices/loadProduct";

import { res } from "../model/response";



const useGetData = () => {


  const [products, setProducts] = useState<Array<ProductProps>>([]);
  const [totalPagesProduct, setTotalPagesProduct] = useState<string>("");
  const [totalElementsProduct, setTotalElementsProduct] = useState("");




  const notReload = () => {
    dispatch(reloadProduct.reloadProduct(false));
  };


  const reload = useSelector((state: TReducers) => state.reload.load);

  const dispatch = useDispatch();

  const page = useSelector((state:TReducers)=>state.changePage.page)
  
  // const cookies = {
  //   anks: getDataFromCookie("user"),
    
  // };
  


  const config = {
   
    withCredentials: true,
    "Content-Type": "application/json",
    
    // Cookie: Object.entries(cookies).map(([key, value]) => `${key}=${value}`).join('; ')
    // 'Access-Control-Allow-Origin': false ,
  };

  

  const getAllProducts = async (page?: number|1) => {

    try {
      await request
      .get1<res>(`products?page=${page}`, { headers: config })
      .then((res) => {
        setProducts(res.content);
        setTotalPagesProduct(res.totalPages);
        setTotalElementsProduct(res.totalElements);
        notReload();
      })
      .catch((err) => {
        console.error(err);
      });
    } catch (error) {
      
    }

  };

  useEffect(() => {
    getAllProducts(Number(page));
  }, [reload]);

  // useEffect(() => {
  //   getAllAccount();
  //   getAllProducts();
  // }, [reload]);

  return {
   
    products,
    
    totalElementsProduct,
    
    totalPagesProduct,
  };
};

export default useGetData;
