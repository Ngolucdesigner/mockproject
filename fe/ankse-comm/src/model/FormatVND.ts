const config = {
    style: "currency",
    currency: "VND",
    maximumFractionDigits: 9,
};
export const priceFormat = (price:number)=>{
   return new Intl.NumberFormat("it-IT", config).format(Number(price));
}