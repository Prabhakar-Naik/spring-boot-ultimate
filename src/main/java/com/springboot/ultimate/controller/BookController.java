package com.springboot.ultimate.controller;

import com.springboot.ultimate.domain.dto.AuthorDto;
import com.springboot.ultimate.domain.dto.BookDto;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.entities.Book;
import com.springboot.ultimate.mappers.Mapper;
import com.springboot.ultimate.service.BookService;
import com.springboot.ultimate.service.impl.BookServiceImpl;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author prabhakar, @Date 06-08-2024
 */
@Log
@RestController
@RequestMapping(value = "/books")
public class BookController {

            /*  These two function for json purposes. if you want to try to modify the path and names   */
//    @GetMapping(path = "/getBook")
//    public Book retrieveBook(){
//        Author author = new Author(1L,"prashanth",45);
//        return Book.builder()
//                .isbn("123-456-789")
//                .title("This is My Life")
//                .authorId(author)
//                .build();
//    }
//
//
//    @PostMapping(path = "/createBook")
//    public Book createBook(@RequestBody final Book book){
//        log.info("Got Book: "+book.toString());
//        return book;
//    }


    private final Mapper<Book, BookDto> bookMapper;

    private final BookService service;

    public BookController(BookService bookService,Mapper<Book, BookDto> bookDtoMapper){
        this.service = bookService;
        this.bookMapper = bookDtoMapper;
    }

    @PostMapping(path = "/createBook")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        Book book = this.bookMapper.mapFrom(bookDto);
        Book book1 = this.service.createBook(book);
        return new ResponseEntity<>(this.bookMapper.mapTo(book1), HttpStatus.CREATED);
    }


    @GetMapping(path = "/getListOfBooks")
    public ResponseEntity<List<BookDto>> getListOfBooks(){
        List<Book> listOfBooks = this.service.getListOfBooks();

        return new ResponseEntity<>(listOfBooks.stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path = "/findALlPagination")
    public ResponseEntity<Page<BookDto>> findAll(Pageable pageable){
        Page<Book> listOfBooks = this.service.findAll(pageable);

        return new ResponseEntity<>(listOfBooks.map(this.bookMapper::mapTo),HttpStatus.OK);
    }


    @GetMapping(path = "/getOneBook/{isbn}")
    public ResponseEntity<BookDto> getOneBook(@PathVariable String isbn){
        Optional<Book> foundBook = this.service.getOneBook(isbn);

        return foundBook.map(author -> {
            BookDto authorDto = this.bookMapper.mapTo(author);
            return new ResponseEntity<>(authorDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping(path = "/updateBook/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable String isbn, @RequestBody BookDto bookDto){
        if (!this.service.isExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookDto.setIsbn(isbn);
        Book book = bookMapper.mapFrom(bookDto);
        Book book1 = this.service.updateBook(book);

        return new ResponseEntity<>(this.bookMapper.mapTo(book1),HttpStatus.OK);
    }


    @PostMapping(path = "/createUpdateBook/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable String isbn, @RequestBody BookDto bookDto){

        Book book = bookMapper.mapFrom(bookDto);
        boolean bookExist = this.service.isExists(isbn);

        Book savedBook = this.service.createupdateBook(isbn,book);
        BookDto saveUpdatedBookDto = this.bookMapper.mapTo(savedBook);
        if (bookExist){
            return new ResponseEntity<>(saveUpdatedBookDto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(saveUpdatedBookDto,HttpStatus.CREATED);
        }
    }


    @PatchMapping(path = "/partialUpdate/{isbn}")
    public ResponseEntity<BookDto> partialUpdate(@PathVariable("isbn") String isbn,
                                                 @RequestBody BookDto bookDto){
        if (!this.service.isExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book book = bookMapper.mapFrom(bookDto);
        Book updatedBook = this.service.partialUpdate(isbn,book);

        return new ResponseEntity<>(this.bookMapper.mapTo(updatedBook),HttpStatus.OK);
    }


    @DeleteMapping(path = "/deleteBook/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn){
        this.service.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
