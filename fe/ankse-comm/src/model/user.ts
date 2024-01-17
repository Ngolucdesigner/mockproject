export type userPops = {
    id:number,
    accountId:string,  
    userName:string,
    email: string,
    fullName: string,
    avatar?: any,
    phone: string,
    address: string,
    role?: string,
    createDate?: number,
    file?:{
      url:string
    }
  };
  