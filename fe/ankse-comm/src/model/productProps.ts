import { Tracing } from "trace_events";

export type ProductProps = {
  id:any;  
  imgUrl: any;
  productName: string;
  price: number;
  category: string;
  shortDesc?: string;
  description?: string;
  reviews?: {
    reviewId:any;
    username:string;
    rating: Number;
    reviewText: String
  }[];
  avgRating?: number;
  file?:{
    id: string,
    fileName: string,
    fileType: string,
    url: string
  };
  origin?:{
    id: any,
    manufacturer: string,
    madeIn: string,
    guarantee: string


  }
};