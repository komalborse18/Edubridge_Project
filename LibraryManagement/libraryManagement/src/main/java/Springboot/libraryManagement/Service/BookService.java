package Springboot.libraryManagement.Service;

import java.util.List;

import Springboot.libraryManagement.Model.Book;

public interface BookService {

	List<Book> readAllBooks();
	
}
