package com.hexaware.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@Column(name = "ISBN", length = 13, nullable = false)
	long isbn;
	
	@Column(name = "Title of Book", length = 60, nullable = false)
	String title;
	
	@Column(name = "Book's Author", length = 50, nullable = false)
	String author;
	
	@Column(name = "Publication Year", length = 4, nullable = false)
	int publicationYear;

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

	public Book(long isbn, String title, String author, int publicationYear) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
	}

	public Book() {
		
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publication_year="
				+ publicationYear + "]";
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publication_year) {
		this.publicationYear = publication_year;
	}

}
