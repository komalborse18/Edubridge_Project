import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Book } from './book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseURL = "http://localhost:8089/librarymanagementsystem.com";

  constructor(private httpClient: HttpClient) { }
  getBookList(): Observable<Book[]>{
    return this.httpClient.get<Book[]>(`${this.baseURL}/getBooks`);

  }

  addBook(book: Book): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/addNewBook`, book);
  }

  getBookByBookId(bookId: number): Observable<Book>{
    return this.httpClient.get<Book>(`${this.baseURL}/getBook/${bookId}`);
  }

  updateBook(bookId: number, book: Book): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/updateBookDetails/${bookId}`, book);
  }
  deleteBook(bookId: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/deleteBookDetails/${bookId}`);
  }
}
