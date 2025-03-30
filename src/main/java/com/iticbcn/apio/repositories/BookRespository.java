package com.iticbcn.apio.repositories;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.iticbcn.apio.models.Book;

@Repository
public interface BookRespository extends CrudRepository<Book, Long> {

    ArrayList<Book> llibres = new ArrayList<Book>();

    @Override
    @NonNull
    public Set<Book> findAll();

    public Book findByTitle(String title);

    public Set<Book> findByTitleAndPublisher(String title, String publisher);
    
}

