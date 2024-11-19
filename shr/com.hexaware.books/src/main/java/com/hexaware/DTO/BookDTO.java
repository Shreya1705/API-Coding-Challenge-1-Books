package com.hexaware.DTO;
import jakarta.validation.constraints.*;
public class BookDTO {
	
	@NotNull(message = "ISBN must not be null")
    @Min(value = 1000000000000L, message = "ISBN must be at least 13 digits")
    @Max(value = 9999999999999L, message = "ISBN must not exceed 13 digits")
    private long isbn;
	
	@NotBlank(message = "Title must not be blank")
	@Size(max = 60, message = "Title must not exceed 60 characters")
    private String title;
	
	@NotBlank(message = "Author must not be blank")
	@Size(max = 50, message = "Author name must not exceed 50 characters")
    private String author;
	
	@NotNull(message = "Publication year must not be null")
    @Min(value = 1000, message = "Publication year must be a valid 4-digit number")
    @Max(value = 9999, message = "Publication year must be a valid 4-digit number")
    private int publicationYear;

    
    public BookDTO() {
    }

    public BookDTO(long isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

   
    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
