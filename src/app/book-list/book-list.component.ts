import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../book';
import { BookService } from '../book.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit{
  [x: string]: any;

  books!: Book[];
  count: number = 4;
  p: number = 1;

  constructor(private bookService: BookService,
    private router: Router, protected userService: UserService) { }

  ngOnInit(): void {
    this.getBooks();
  }

  private getBooks(){
    this.bookService.getBookList().subscribe(data => {
      this.books = data;
    });
  }

  viewBook(bookId: number){
    this.router.navigate(['view-book', bookId]);
  }

  updateBook(bookId: number){
    this.router.navigate(['update-book', bookId]);
  }
  deleteBook(bookId: number){
    this.bookService.deleteBook(bookId).subscribe( data => {
      console.log(data);
      this.getBooks();
    })
  }
}
