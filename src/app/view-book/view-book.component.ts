import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { BookService } from '../book.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-book',
  templateUrl: './view-book.component.html',
  styleUrls: ['./view-book.component.css']
})
export class ViewBookComponent implements  OnInit{

  bookId!: number;
  book!: Book;
  constructor(private route: ActivatedRoute, private bookService: BookService) { }

  ngOnInit(): void {
    this.bookId = this.route.snapshot.params['bookId'];

    this.book = new Book();
    this.bookService.getBookByBookId(this.bookId).subscribe( data => {
      this.book = data;
    });
  }

}
