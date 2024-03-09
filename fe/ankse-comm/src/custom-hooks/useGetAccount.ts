import  { useEffect, useState } from "react";
import { userPops } from "../model/user";
import { useDispatch, useSelector } from "react-redux";

import { TReducers } from "../redux/rootReducer";
import { getDataFromCookie } from "../Utils/customCookie";
import * as request from "../Utils/request";
import { res } from "../model/response";
import { reloadAccount } from "../redux/slices/loadAccount";
const useGetAccount = () => {
  const [useData, setUseData] = useState<Array<userPops>>([]);
  const [totalPagesAccount, setTotalPagesAccount] = useState("");
  const [totalElementsAccount, setTotalElementsAccount] = useState("");
  const dispatch = useDispatch();

  const notReload = () => {
    dispatch(reloadAccount.reloadAccount(false));
  };
  const reload = useSelector((state: TReducers) => state.reloadAccount.load);

  const page = useSelector((state: TReducers) => state.changePage.page);

  // const auth = getDataFromCookie("user")
  const config = {
    withCredentials: true,
    "Content-Type": "application/json",
    Authorization: `Bearer ${getDataFromCookie("user")}`,
    // Authorization: "Bearer " + getDataFromCookie("user"),
    // 'Access-Control-Allow-Origin': false ,
  };

 

  const getAllAccount = async () => {
    try {
      await request
        .get1<res>("accounts", { headers: config })
        .then((res) => {
         
          setUseData(res.content);
          setTotalPagesAccount(res.totalPages);
          setTotalElementsAccount(res.totalElements);
          notReload();
        })
        .catch((err) => {
          console.error(err);
        });
    } catch (error) {}
  };

  useEffect(() => {
    getAllAccount();
  }, [reload]);

  return {
    useData,
    totalElementsAccount,
    totalPagesAccount,
  };
};

export default useGetAccount;
