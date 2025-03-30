package com.iticbcn.apio.services;

import java.util.Optional;
import java.util.Set;

import com.iticbcn.apio.models.Book;

public interface BookService {
  
  Set<Book> findAll();
  Book findByTitle(String title);
  Set<Book> findByTitolAndEditorial(String title, String publisher);
  boolean isValidISBN(String isbn); // 3 2 3 4 1
  Optional<Book> findById(long id);
  Book save(Book book);


}
