package com.hexaware.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.Entity.Book;



public interface BookRepo extends JpaRepository<Book, Long>{

	Optional<Book> findByIsbn(Long isbn);
}
