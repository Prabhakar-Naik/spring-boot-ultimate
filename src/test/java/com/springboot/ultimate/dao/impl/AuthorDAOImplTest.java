/*
package com.springboot.ultimate.dao.impl;

import com.springboot.ultimate.TestDataUtil;
import com.springboot.ultimate.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

*/
/**
 * @author prabhakar, @Date 29-07-2024
 *//*

//Jdbc api

@ExtendWith(MockitoExtension.class)
public class AuthorDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDAOImpl underTest;

    @Test
    public void testThatCreateAuthorGenerateCorrectSql() {

        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id,name,age) VALUES (?,?,?)"),
                eq(1L),
                eq("prabhakar"),
                eq(24));
    }

    @Test
    public void testThatFindOneGenerateTheCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDAOImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindGenerateTheCorrectSql() {
        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDAOImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {

        Author author = TestDataUtil.createTestAuthorA();
        underTest.update(author.getId(), author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                1L, "prabhakar", 24, 1L
        );
    }


    @Test
    public void testThatDeleteGeneratesCorrectSql(){
        underTest.deleteOne(1L);

        verify(jdbcTemplate).update(
                "DELETE FROM authors where id = ?",
                1L
        );
    }


    @Test
    public void testThatDeleteAllGeneratesCorrectSql(){
        underTest.delete();
        verify(jdbcTemplate).update("DELETE FROM authors");
    }



}
*/
