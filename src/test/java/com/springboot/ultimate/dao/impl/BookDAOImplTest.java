package com.springboot.ultimate.dao.impl;

import com.springboot.ultimate.TestDataUtil;
import com.springboot.ultimate.domain.Author;
import com.springboot.ultimate.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * @author prabhakar, @Date 29-07-2024
 */
//@ExtendWith(MockitoExtension.class)
public class BookDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDAOImpl underTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testThatCreateAuthorGenerateCorrectSql(){
//
////        Book book = TestDataUtil.creatTestBookA();
//        Book book = new Book("98-765-432", "This shadow in the Attic", 1L);
//        underTest.create(book);
//
//        verify(jdbcTemplate).update(
//               "INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)",
//                "98-765-432",
//                "This shadow in the Attic",
//                1L);
//    }

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        // Given a book object
        Book book = new Book("98-765-432", "This shadow in the Attic", 1L);

        // When create method is called
        underTest.create(book);

        // Then verify that the correct SQL query is generated
        verify(jdbcTemplate).update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                "98-765-432",
                "This shadow in the Attic",
                1L
        );
    }


    @Test
    public void testThatFindOneBookGeneratesCorrectSql(){
        underTest.findOne("98-765-432");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id from books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDAOImpl.BookRowMapper>any(),
                eq("98-765-432")
        );
    }


    @Test
    public void testThatFindBooksGeneratesCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDAOImpl.BookRowMapper>any()
        );
    }


    @Test
    public void testThatUpdateGeneratesCorrectSql() {

        Book book = TestDataUtil.creatTestBookA();
        underTest.update(book.getIsbn(), book);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "98-765-4323", "This shadow in the Attic", 1L, "98-765-4323"
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql(){
        underTest.deleteOne("98-765-4323");

        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                "98-765-4323"
        );
    }

    @Test
    public void testThatDeleteAllGeneratesCorrectSql(){
        underTest.delete();
        verify(jdbcTemplate).update("DELETE FROM books");
    }




}
