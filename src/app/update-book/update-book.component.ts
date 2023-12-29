import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { BookService } from '../book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-book',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  bookId!: number;
  book: Book = new Book();
  constructor(private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router) { }


  ngOnInit(): void {
    this.bookId = this.route.snapshot.params['bookId'];

    this.bookService.getBookByBookId(this.bookId).subscribe(data => {
      this.book = data;
    }, error => console.log(error));
  }

  onSubmit() {
    this.bookService.updateBook(this.bookId, this.book).subscribe(data => {
      this.goToBookList();
    }
      , error => console.log(error));
  }

  goToBookList() {
    this.router.navigate(['/books']);
  }

}
