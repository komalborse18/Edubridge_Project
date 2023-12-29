import { User } from "./user";

export class Book{
    bookId!:number;
    bookName!:String;
    author!:String;
    borrowed!:boolean;
    isAvailable!:boolean;
    borrowedby!:User
}