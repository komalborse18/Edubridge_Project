package Springboot.libraryManagement.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import Springboot.libraryManagement.Model.Book;
import Springboot.libraryManagement.Model.User;
import Springboot.libraryManagement.Repository.BookRepository;
import Springboot.libraryManagement.Repository.UserRepository;
import Springboot.libraryManagement.Service.BookService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmailService emailService;

	//READ ALL BOOKS
	@Override
	public List<Book> readAllBooks() {
		return bookRepo.findAll();
	}
	
	//READ BOOK BY ID
	public Book getBookByBookId(Integer bookId) {
		return bookRepo.findByBookId(bookId);
	}

	//ADD BOOK
	public boolean addBookDetails(Book book) {
		if (bookRepo.save(book) != null) {
			emailService.sendEmail("sonykonda1@gmail.com", "Book Added Successfully", "Congratulations...!\nNew Book Added Successfully.\n\tBook Details: \n\t\t" + book);
			return true;
		} else {
			return false;
		}
	}
	
	//UPDATE BOOK
	public boolean updateBookDetails(Integer bookId, Book book) {
		Book bookObjFromDb = bookRepo.findByBookId(bookId); 
		if(bookObjFromDb != null)	{
			bookObjFromDb.setIsAvailable(book.getIsAvailable());
			bookRepo.save(bookObjFromDb);
			return true;
		}
		return false;
	}

	//DELETE BOOK BY ID
	public boolean deleteBookDetails(Integer bookId) {
		Book bookObjFromDb = bookRepo.findByBookId(bookId); 
		if(bookObjFromDb != null)	{
//			String str = OTPGeneratorImpl.generateOtp();
//			PhoneNumber to = new PhoneNumber("+916300133146");  // receiver mobile number 
//			PhoneNumber from = new PhoneNumber("+12244124374");  //  sender's twilio number 
//			String otpMessage = "Dear Customer, your OTP is " + str + " for deleting Book details through librarymanagementsystem.com \nThank You.";
//			Message message = Message.creator(to, from, otpMessage).create();
			bookRepo.deleteByBookId(bookId);
			return true;
		}
		return false;
	}
	
	//BORROW BOOK
	public boolean borrowBook(Integer bookId,Integer userId) {
        Book bookObjFromDb = bookRepo.findByBookId(bookId);
        User user = userRepo.findById(userId).orElse(null);
        if (bookObjFromDb != null && !bookObjFromDb.getBorrowed() && user != null) {
        	bookObjFromDb.setBorrowedby(user);
        	bookObjFromDb.setBorrowed(true);
            bookRepo.save(bookObjFromDb);
            return true;
        }
        // Handle errors (e.g., book not found, book already borrowed, user not found)
        return false;
    }

	//RETURN BOOK
    public boolean returnBook(Integer bookId) {
    	Book bookObjFromDb = bookRepo.findByBookId(bookId);
        if (bookObjFromDb != null && !bookObjFromDb.getBorrowed()) {
        	bookObjFromDb.setBorrowedby(null);
        	bookObjFromDb.setBorrowed(false);
            bookRepo.save(bookObjFromDb);
            return true;
        }
        // Handle errors (e.g., book not found, book not borrowed)
        return false;
    }
}
