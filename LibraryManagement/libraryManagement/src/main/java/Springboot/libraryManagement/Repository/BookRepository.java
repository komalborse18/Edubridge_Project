package Springboot.libraryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Springboot.libraryManagement.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	Book findByBookId(Integer bookId);
	Integer deleteByBookId(Integer bookId);
}
