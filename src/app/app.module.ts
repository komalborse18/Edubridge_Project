import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookListComponent } from './book-list/book-list.component';
import { AddBookComponent } from './add-book/add-book.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { ViewBookComponent } from './view-book/view-book.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { BackButtonDisableModule } from 'angular-disable-browser-back-button';
import { SignOutComponent } from './sign-out/sign-out.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { DeletebookComponent } from './deletebook/deletebook.component';


@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    ViewBookComponent,
    SignInComponent,
    SignUpComponent,
    SignOutComponent,
    DeletebookComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BackButtonDisableModule.forRoot({ preserveScroll: true }),
    FormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
