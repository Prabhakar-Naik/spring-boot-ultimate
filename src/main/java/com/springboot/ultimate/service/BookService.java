package com.springboot.ultimate.service;

import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author prabhakar, @Date 07-08-2024
 */
public interface BookService {

    Book createBook(Book book);

    List<Book> getListOfBooks();

    Page<Book> findAll(Pageable pageable);

    Optional<Book> getOneBook(String isbn);

    boolean isExists(String isbn);

    Book updateBook(Book book);

    Book createupdateBook(String isbn, Book book);

    Book partialUpdate(String isbn, Book book);

    void deleteBook(String isbn);
}
