package com.springboot.ultimate.repository;

import com.springboot.ultimate.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author prabhakar, @Date 06-08-2024
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("SELECT a from  Author a where a.age < ?1")
    List<Author> ageLessThan(int age);

    @Query("SELECT a from Author a where a.age > ?1")
    List<Author> findAuthorsWithAgeGreaterThan(int age);
}
