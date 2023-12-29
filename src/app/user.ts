export class User{
    public userId:number=0;
    public name:string;
    public password:string;
    public email:string;
    public designation:string;
  
    constructor(userId:any,name:any,password:any,email:any, designation:any)
    {
     this.userId=userId;
      this.name=name;
      this.email=email;
      this.password=password;
      this.designation=designation;
    }
}