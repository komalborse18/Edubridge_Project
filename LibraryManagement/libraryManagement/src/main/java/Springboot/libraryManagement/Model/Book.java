package Springboot.libraryManagement.Model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DynamicUpdate
public class Book {
	
	@Id
	private Integer bookId;
	private String bookName;
	private String author;
	private Boolean borrowed;
	private Boolean isAvailable;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User borrowedby;
	
	public Book() 
	{
		super();
	}
	
	public Book(Integer bookId, String bookName, String author, Boolean borrowed, Boolean isAvailable,User borrowedBy) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.borrowed = borrowed;
		this.isAvailable = isAvailable;
		this.borrowedby=borrowedBy;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(Boolean borrowed) {
		this.borrowed = borrowed;
	}

	public User getBorrowedby() {
		return borrowedby;
	}

	public void setBorrowedby(User borrowedby) {
		this.borrowedby = borrowedby;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", borrowed=" + borrowed
				+ ", isAvailable=" + isAvailable + ", getIsAvailable()=" + getIsAvailable() + ", getBookId()="
				+ getBookId() + ", getBookName()=" + getBookName() + ", getAuthor()=" + getAuthor() + ", getBorrowed()="
				+ getBorrowed() + ", getBorrowedby()=" + getBorrowedby() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
