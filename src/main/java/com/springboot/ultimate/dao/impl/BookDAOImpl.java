/*
package com.springboot.ultimate.dao.impl;

import com.springboot.ultimate.dao.BookDAO;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.entities.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

*/
/**
 * @author prabhakar, @Date 29-07-2024
 *//*

//Jdbc api
@Service
public class BookDAOImpl implements BookDAO {

    private final JdbcTemplate jdbcTemplate;


    public BookDAOImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//    @Override
//    public void create(Book book) {
//        jdbcTemplate.update(
//                "INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)",
//                book.getIsbn(),
//                book.getTitle(),
//                book.getAuthorId()
//        );
//    }

    @Override
    public void create(Book book) {
        String sql = "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getAuthorId());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id from books WHERE isbn = ? LIMIT 1",
                new BookDAOImpl.BookRowMapper(),
                isbn);

        return results.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BookRowMapper()
        );
    }

    @Override
    public void update(String isbn, Book book) {
         jdbcTemplate.update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(),book.getTitle(),book.getAuthorId(),isbn
        );
    }

    @Override
    public void deleteOne(String isbn) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn = ?",
                isbn
        );
    }

    @Override
    public void delete() {
        jdbcTemplate.update("DELETE FROM books");
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }

}
*/
