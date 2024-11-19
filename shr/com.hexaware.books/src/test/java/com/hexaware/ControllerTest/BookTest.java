package com.hexaware.ControllerTest;

import com.hexaware.Service.BookService;

import com.hexaware.Controller.BookController;
import com.hexaware.DTO.BookDTO;
import com.hexaware.Entity.Book;
import com.hexaware.Exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class BookTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testDeleteBook_Success() throws Exception {
        long isbn = 9780321125217L;
        when(bookService.deleteBook(isbn)).thenReturn(true);

        mockMvc.perform(delete("/api/deleteBook/{isbn}", isbn))
               .andExpect(status().isNoContent())
               .andExpect(content().string("Book with ISBN " + isbn + " was successfully deleted."));
    }

    @Test
    void testDeleteBook_BookNotFound() throws Exception {
        long isbn = 9780321125217L;
        when(bookService.deleteBook(isbn)).thenReturn(false);

        mockMvc.perform(delete("/api/deleteBook/{isbn}", isbn))
               .andExpect(status().isNotFound())
               .andExpect(content().string("Book with ISBN " + isbn + " not found."));
    }
    
    
    
    
    
    ////
    @Test
    void testAddBook_Success() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn(9780134685991L);
        bookDTO.setTitle("Clean Code");
        bookDTO.setAuthor("Robert C. Martin");
        bookDTO.setPublicationYear(2008);

        Book savedBook = new Book(
                bookDTO.getIsbn(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getPublicationYear()
        );

        when(bookService.addBook(any(Book.class))).thenReturn(savedBook);

        ResponseEntity<Book> response = bookController.addBook(bookDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedBook, response.getBody());
        verify(bookService, times(1)).addBook(any(Book.class));
    }
    
    @Test
    void testGetBookByIsbn_Success() {
        long isbn = 9780134685991L;
        BookDTO bookDTO = new BookDTO(isbn, "Clean Code", "Robert C. Martin", 2008);

        when(bookService.getBookByIsbn(isbn)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.getBookByIsbn(isbn);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDTO, response.getBody());
        verify(bookService, times(1)).getBookByIsbn(isbn);
    }

    @Test
    void testGetBookByIsbn_NotFound() {
        long isbn = 9780134685991L;
        when(bookService.getBookByIsbn(isbn)).thenReturn(null);

        ResponseEntity<BookDTO> response = bookController.getBookByIsbn(isbn);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).getBookByIsbn(isbn);
    }

    
    @Test
    void testGetAllBooks_Success() {
       
        BookDTO book1 = new BookDTO(9780134685991L, "Clean Code", "Robert C. Martin", 2008);
        BookDTO book2 = new BookDTO(9780132350884L, "The Pragmatic Programmer", "Andrew Hunt", 1999);
        List<BookDTO> books = List.of(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        
        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();

      
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());  // Check the size of the list
        assertEquals(book1, response.getBody().get(0));  // Check the first book
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testUpdateBook_Success() {
        long isbn = 9780134685991L;
        BookDTO bookDTO = new BookDTO(isbn, "Clean Code", "Robert C. Martin", 2008);
        BookDTO updatedBookDTO = new BookDTO(isbn, "Clean Code (Updated)", "Robert C. Martin", 2020);

        when(bookService.updateBook(isbn, bookDTO)).thenReturn(updatedBookDTO);

        ResponseEntity<BookDTO> response = bookController.updateBook(isbn, bookDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBookDTO, response.getBody());
        verify(bookService, times(1)).updateBook(isbn, bookDTO);
    }

    @Test
    void testUpdateBook_NotFound() {
        long isbn = 9780134685991L;
        BookDTO bookDTO = new BookDTO(isbn, "Clean Code", "Robert C. Martin", 2008);

        when(bookService.updateBook(isbn, bookDTO)).thenReturn(null);

        ResponseEntity<BookDTO> response = bookController.updateBook(isbn, bookDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).updateBook(isbn, bookDTO);
    }

}
