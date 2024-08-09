package com.springboot.ultimate.service.impl;

import com.springboot.ultimate.domain.entities.Book;
import com.springboot.ultimate.repository.BookRepository;
import com.springboot.ultimate.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author prabhakar, @Date 07-08-2024
 */

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.repository = bookRepository;
    }


    @Override
    public Book createBook(Book book) {
        return this.repository.save(book);
    }

    @Override
    public List<Book> getListOfBooks() {
        return StreamSupport.stream(this.repository.findAll().stream().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public Optional<Book> getOneBook(String isbn) {
        return this.repository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return this.repository.existsById(isbn);
    }

    @Override
    public Book updateBook(Book book) {
        return this.repository.save(book);
    }

    @Override
    public Book createupdateBook(String isbn, Book book) {
        book.setIsbn(isbn);
        return this.repository.save(book);
    }

    @Override
    public Book partialUpdate(String isbn, Book book) {
        book.setIsbn(isbn);
        return this.repository.findById(isbn).map(existBook -> {
            if (book.getTitle() != null)
                Optional.of(book.getTitle()).ifPresent(existBook::setTitle);
            else
                book.setTitle(existBook.getTitle());
//            if (book.getAuthorId() != null)
            Optional.ofNullable(book.getAuthorId()).ifPresent(existBook::setAuthorId);
//            else
//                book.setAuthorId(existBook.getAuthorId());


            return this.repository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book Does not exist"));
    }

    @Override
    public void deleteBook(String isbn) {
        this.repository.deleteById(isbn);
    }

}
