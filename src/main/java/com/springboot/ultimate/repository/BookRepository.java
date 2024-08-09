package com.springboot.ultimate.repository;

import com.springboot.ultimate.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author prabhakar, @Date 06-08-2024
 */

@Repository
public interface BookRepository extends JpaRepository<Book, String>,
                                        PagingAndSortingRepository<Book, String>{

}
