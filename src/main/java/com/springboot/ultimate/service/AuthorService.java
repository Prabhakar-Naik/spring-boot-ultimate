package com.springboot.ultimate.service;

import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.dto.AuthorDto;
import com.springboot.ultimate.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author prabhakar, @Date 07-08-2024
 */

public interface AuthorService {

    Author createAuthor(Author author);

    List<Author> getListOfAuthors();

    Optional<Author> getOneAuthor(Long id);

    boolean isExists(Long id);

    Author updateAuthor(Author author);

    Author partialUpdate(Long id, Author author);

    void deleteAuthor(Long id);
}
