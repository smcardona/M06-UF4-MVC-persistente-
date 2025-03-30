package com.iticbcn.apio.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iticbcn.apio.models.Book;
import com.iticbcn.apio.repositories.BookRespository;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRespository repositori;

  public Set<Book> findAll() {
    return repositori.findAll();
  }

  public Book findByTitle(String title) {
    return repositori.findByTitle(title);
  }

  public Set<Book> findByTitolAndEditorial(String title, String publisher) {
    return repositori.findByTitleAndPublisher(title, publisher);
  }

  public boolean isValidISBN(String isbn) {

    if (isbn == null || isbn.isEmpty()) {
      return false;
    }

    String regex = "^\\d{3}-\\d{2}-\\d{3}-\\d{4}-\\d{1}$";
    return isbn.matches(regex);

  }

  public Optional<Book> findById(long id) {
    return repositori.findById(id);
  }

  public Book save(Book book) {
    return repositori.save(book);
  }

  
}
