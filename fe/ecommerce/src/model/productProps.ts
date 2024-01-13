export type ProductProps = {
  id:string;  
  imgUrl: any;
  productName: string;
  price: number;
  category: string;
  shortDesc?: string;
  description?: string;
  reviews?: {
    rating: Number;
    text: String
  }[];
  avgRating?: number;
};
