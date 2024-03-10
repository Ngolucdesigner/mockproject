export type userPops = {
    id:number,
    accountId:string,  
    username:string,
    email: string,
    fullName: string,
    firstName:string,
    lastName:string,
    avatar?: any,
    phone: string,
    address: string,
    role?: string,
    createDate?: number,
    active?: boolean,
    file?:{
      fileId:string,
      url:string
    }
  };
  