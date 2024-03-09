export type loginInfo ={
    id: any,
    username: string
    email: string,
    token: string;
    roles: Array<string>;
    avatar:{
        id: any,
        fileName: string,
        fileType: string,
        url: string
    }
    // headers?:any
}