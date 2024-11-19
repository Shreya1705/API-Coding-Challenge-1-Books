package com.hexaware.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.DTO.BookDTO;
import com.hexaware.Entity.Book;
import com.hexaware.Service.BookService;

import jakarta.validation.Valid;





@RestController
@RequestMapping("/api")
public class BookController {
	
	
	@Autowired
    private BookService ser;
	

	 
	 @PostMapping("/addBook")
	    public ResponseEntity<Book> addBook(@Valid @RequestBody BookDTO bookDTO) {
	        Book book = new Book(
	            bookDTO.getIsbn(),
	            bookDTO.getTitle(),
	            bookDTO.getAuthor(),
	            bookDTO.getPublicationYear()
	        );
	        Book savedBook = ser.addBook(book);
	        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	    }
	 
	 @GetMapping("/getBooks")
	    public ResponseEntity<List<BookDTO>> getAllBooks() {
	        List<BookDTO> books = ser.getAllBooks();
	        return new ResponseEntity<>(books, HttpStatus.OK);
	    }
	 
	 @GetMapping("/findBook/{isbn}")
	    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable Long isbn) {
	        BookDTO bookDTO = ser.getBookByIsbn(isbn);
	        if (bookDTO != null) {
	            return new ResponseEntity<>(bookDTO, HttpStatus.OK); 
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	        }
	    }
	 
	 @PutMapping("/updateBook/{isbn}")
	    public ResponseEntity<BookDTO> updateBook(@PathVariable Long isbn, @RequestBody BookDTO bookDTO) {
	        BookDTO updatedBook = ser.updateBook(isbn, bookDTO);

	        if (updatedBook != null) {
	            return new ResponseEntity<>(updatedBook, HttpStatus.OK);  
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
	        }
	    }
	 @DeleteMapping("/deleteBook/{isbn}")
	    public ResponseEntity<Object> deleteBook(@PathVariable Long isbn) {
	        boolean isDeleted = ser.deleteBook(isbn);

	        if (isDeleted) {

	            return new ResponseEntity<>(
	                    "Book with ISBN " + isbn + " was successfully deleted.", 
	                    HttpStatus.NO_CONTENT);
	        } else {
	            
	            return new ResponseEntity<>(
	                    "Book with ISBN " + isbn + " not found.", 
	                    HttpStatus.NOT_FOUND);
	        }
	    }

}
