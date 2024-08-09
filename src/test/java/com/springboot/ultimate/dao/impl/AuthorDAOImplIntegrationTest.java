/*
package com.springboot.ultimate.dao.impl;

import com.springboot.ultimate.TestDataUtil;
import com.springboot.ultimate.domain.entities.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

*/
/**
 * @author prabhakar, @Date 30-07-2024
 *//*

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDAOImplIntegrationTest {

    private final AuthorDAOImpl underTest;

    @Autowired
    public AuthorDAOImplIntegrationTest(AuthorDAOImpl authorDAO){
        this.underTest = authorDAO;
    }


    @Test
    public void testThatAuthorCanBeCreatedAndCalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
       Optional<Author> result = underTest.findOne(author.getId());

       assertThat(result).isPresent();
       assertThat(result.get()).isEqualTo(author);
    }


    @Test
    public void testThatMultipleAuthorsCanBeCreatedRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        Author author1 = TestDataUtil.createTestAuthorB();
        underTest.create(author1);
        Author author2 = TestDataUtil.createTestAuthorC();
        underTest.create(author2);
        Author author3 = TestDataUtil.createTestAuthorD();
        underTest.create(author3);

        List<Author> result = underTest.find();
        assertThat(result)
                .hasSize(4)
                .containsExactly(author, author1, author2, author3);

    }


    @Test
    public void testThatAuthorCanUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);

        author.setName("Updated");

        underTest.update(author.getId(),author);

        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }


    @Test
    public void testThatAuthorCanDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);

        underTest.deleteOne(author.getId());

        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isEmpty();

    }

    @Test
    public void testThatAuthorsCanDeletedAll(){
        underTest.delete();
        List<Author> result = underTest.find();

        assertThat(result).isEmpty();
    }






}
*/
