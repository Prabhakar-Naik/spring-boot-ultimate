package com.springboot.ultimate.service.impl;

import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.repository.AuthorRepository;
import com.springboot.ultimate.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author prabhakar, @Date 07-08-2024
 */
@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.repository = authorRepository;
    }


    @Override
    public Author createAuthor(Author author) {
        return this.repository.save(author);
    }

    @Override
    public List<Author> getListOfAuthors() {
        return StreamSupport.stream(this.repository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Author> getOneAuthor(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return !this.repository.existsById(id);
    }

    @Override
    public Author updateAuthor(Author author) {
        return this.repository.save(author);
    }

    @Override
    public Author partialUpdate(Long id, Author author) {
        author.setId(id);
        return this.repository.findById(id).map( existAuthor -> {
            if (author.getName() != null)
                Optional.of(author.getName()).ifPresent(existAuthor::setName);
            else
                author.setName(existAuthor.getName());
            if (author.getAge() != null)
                Optional.of(author.getAge()).ifPresent(existAuthor::setAge);
            else
                author.setAge(existAuthor.getAge());


         return this.repository.save(author);
        }).orElseThrow(() -> new RuntimeException("Author Does not exist"));
    }

    @Override
    public void deleteAuthor(Long id) {
        this.repository.deleteById(id);
    }
}
