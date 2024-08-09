package com.springboot.ultimate.controller;

import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.dto.AuthorDto;
import com.springboot.ultimate.mappers.Mapper;
import com.springboot.ultimate.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author prabhakar, @Date 06-08-2024
 */
@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private final AuthorService service;

    private final Mapper<Author, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<Author, AuthorDto> authorMapper){
        this.service = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/createAuthor")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto){

        Author author = this.authorMapper.mapFrom(authorDto);
        Author author1 = this.service.createAuthor(author);
        return new ResponseEntity<>(this.authorMapper.mapTo(author1), HttpStatus.CREATED);
    }


    @GetMapping(path = "/getListOfAuthors")
    public ResponseEntity<List<AuthorDto>> getListOfAuthors(){
        List<Author> authorDtoList = this.service.getListOfAuthors();

        return new ResponseEntity<>(authorDtoList.stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList()), HttpStatus.OK);
    }


    @GetMapping(path = "/getOneAuthor/{id}")
    public ResponseEntity<AuthorDto> getOneAuthor(@PathVariable Long id){
        Optional<Author> foundAuthor = this.service.getOneAuthor(id);

        return foundAuthor.map(author -> {
            AuthorDto authorDto = this.authorMapper.mapTo(author);
            return new ResponseEntity<>(authorDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping(path = "/updateAuthor/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto){
        if (this.service.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);
        Author author = authorMapper.mapFrom(authorDto);
        Author author1 = this.service.updateAuthor(author);

        return new ResponseEntity<>(this.authorMapper.mapTo(author1),HttpStatus.OK);
    }


    @PatchMapping(path = "/partialUpdate/{id}")
    public ResponseEntity<AuthorDto> partialUpdate(@PathVariable("id") Long id,
                                                   @RequestBody AuthorDto authorDto){
        if (this.service.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author author = authorMapper.mapFrom(authorDto);
        Author updatedAuthor = this.service.partialUpdate(id,author);

        return new ResponseEntity<>(this.authorMapper.mapTo(updatedAuthor),HttpStatus.OK);
    }


    @DeleteMapping(path = "/deleteAuthor/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        this.service.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
