package Springboot.libraryManagement.Controller;

import java.lang.module.ResolutionException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Springboot.libraryManagement.Model.Book;
import Springboot.libraryManagement.Model.User;
import Springboot.libraryManagement.Repository.BookRepository;
import Springboot.libraryManagement.Repository.UserRepository;
import Springboot.libraryManagement.ServiceImplementation.BookServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/librarymanagementsystem.com")
public class BookController {
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private BookRepository bookRepository;

	//READ ALL BOOKS
	@GetMapping("/getBooks")
	List<Book> getAllBook() {
		return bookServiceImpl.readAllBooks();
	}
	
	//READ BOOK BY ID
	@GetMapping("/getBook/{bookId}")
	public ResponseEntity<Book> getUserById(@PathVariable Integer bookId) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResolutionException("Book not exist with bookId :" + bookId));
		return ResponseEntity.ok(book);
	}

	//ADD BOOK
	@PostMapping("/addNewBook")
	void addBook(@RequestBody Book book) {
		boolean isAdded = bookServiceImpl.addBookDetails(book);
		if (isAdded) {
			System.out.println("New Book details Added.");
		} else {
			System.out.println("Failed to add new Book details.");
		}
	}

	//UPDATE BOOK
	@PutMapping("/updateBookDetails/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @RequestBody Book bookDetails){
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResolutionException("Book not exist with bookId :" + bookId));
		
		book.setBookName(bookDetails.getBookName());
		book.setAuthor(bookDetails.getAuthor());
		book.setBorrowed(bookDetails.getBorrowed());
		book.setBorrowedby(bookDetails.getBorrowedby());
		book.setIsAvailable(bookDetails.getIsAvailable());
		Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}

	//DELETE BOOK BY ID
	@DeleteMapping("/deleteBookDetails/{bookId}")
	void deleteBook(@PathVariable("bookId") Integer bookId) {
		boolean isDeleted = bookServiceImpl.deleteBookDetails(bookId);
		if (isDeleted) {
			System.out.println("Book details deleted.");
		} else {
			System.out.println("Failed to delete Book details.");
		}
	}
	
	//BORROW BOOK
	@PostMapping("/{bookId}/borrow/{userId}")
    public Boolean borrowBook(@PathVariable Integer bookId,@PathVariable Integer userId) {
        boolean borrowedBook = bookServiceImpl.borrowBook(bookId, userId);
        if (borrowedBook) {
            System.out.println("Book is borrowed. ");
        } else {
            System.out.println("Book is not borrowed. "); // or a more descriptive error response
        }
		return borrowedBook;
    }

	//RETURN BOOK
    @PostMapping("/{bookId}/return")
    public Boolean returnBook(@PathVariable Integer bookId) {
        boolean returnedBook = bookServiceImpl.returnBook(bookId);
        if (returnedBook) {
        	System.out.println("Book is returned. ");
        } else {
        	System.out.println("Book is not returned. ");// or a more descriptive error response
        }
		return returnedBook;
    }
}
