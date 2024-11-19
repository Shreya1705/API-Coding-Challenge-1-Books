package com.hexaware.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.DTO.BookDTO;
import com.hexaware.Entity.Book;
import com.hexaware.Exception.BookNotFoundException;
import com.hexaware.Repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo rep;

	 public Book addBook(Book book) {
	        return rep.save(book);
	    }
	 
	 public List<BookDTO> getAllBooks() {
	        List<Book> books = rep.findAll();
	        return books.stream()
	                .map(book -> new BookDTO(
	                    book.getIsbn(),
	                    book.getTitle(),
	                    book.getAuthor(),
	                    book.getPublicationYear()
	                ))
	                .collect(Collectors.toList());
	    }
	 
	 public BookDTO getBookByIsbn(Long isbn) {
	        Optional<Book> book = rep.findByIsbn(isbn);
	        if (book.isPresent()) {
	            Book b = book.get();
	            return new BookDTO(
	                    b.getIsbn(),
	                    b.getTitle(),
	                    b.getAuthor(),
	                    b.getPublicationYear()
	            );
	        } else {
	        	throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");  
	        }
	    }
	 
	 public BookDTO updateBook(Long isbn, BookDTO bookDTO) {
	        Optional<Book> existingBookOpt = rep.findByIsbn(isbn);

	        if (existingBookOpt.isPresent()) {
	            Book existingBook = existingBookOpt.get();
	            existingBook.setTitle(bookDTO.getTitle());
	            existingBook.setAuthor(bookDTO.getAuthor());
	            existingBook.setPublicationYear(bookDTO.getPublicationYear());

	          
	            rep.save(existingBook);

	            return new BookDTO(
	                    existingBook.getIsbn(),
	                    existingBook.getTitle(),
	                    existingBook.getAuthor(),
	                    existingBook.getPublicationYear()
	            );
	        } else {
	        	throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");  
	            
	        }
	    }
	 
	 public boolean deleteBook(Long isbn) {
	        Optional<Book> existingBookOpt =rep.findByIsbn(isbn);

	        if (existingBookOpt.isPresent()) {
	            rep.delete(existingBookOpt.get());
	            return true;  
	        } else {
	        	throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
	        }
	    }
}
