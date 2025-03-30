package com.iticbcn.apio.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long bookId;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String publisher;

    @Column(name="publication_date")
    private LocalDate publicationDate;

    @Column
    private String genre;

    @Column(name="isbn", unique = true, nullable = false)
    private String ISBN;


}
