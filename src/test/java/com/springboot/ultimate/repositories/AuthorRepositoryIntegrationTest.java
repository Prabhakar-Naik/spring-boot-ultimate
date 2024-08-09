package com.springboot.ultimate.repositories;

import com.springboot.ultimate.TestDataUtil;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author prabhakar, @Date 30-07-2024
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {


    private final AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository authorDAO){
        this.underTest = authorDAO;
    }


    @Test
    public void testThatAuthorCanBeCreatedAndCalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
       Optional<Author> result = underTest.findById(author.getId());

       assertThat(result).isPresent();
       assertThat(result.get()).isEqualTo(author);
    }


    @Test
    public void testThatMultipleAuthorsCanBeCreatedRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Author author1 = TestDataUtil.createTestAuthorB();
        underTest.save(author1);
        Author author2 = TestDataUtil.createTestAuthorC();
        underTest.save(author2);
        Author author3 = TestDataUtil.createTestAuthorD();
        underTest.save(author3);

        List<Author> result = underTest.findAll();
        assertThat(result)
                .hasSize(4)
                .containsExactly(author, author1, author2, author3);

    }


    @Test
    public void testThatAuthorCanUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);

        author.setName("Updated");
        underTest.save(author);

        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }


    @Test
    public void testThatAuthorCanDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);

        underTest.deleteById(author.getId());

        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isEmpty();

    }


    @Test
    public void testThatAuthorsCanDeletedAll(){
        underTest.deleteAll();
        List<Author> result = underTest.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Author author1 = TestDataUtil.createTestAuthorB();
        underTest.save(author1);
        Author author2 = TestDataUtil.createTestAuthorC();
        underTest.save(author2);
        Author author3 = TestDataUtil.createTestAuthorD();
        underTest.save(author3);

        List<Author> result = underTest.ageLessThan(35);
        assertThat(result)
                .hasSize(3)
                .containsExactly(author, author1, author2);
    }


    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Author author1 = TestDataUtil.createTestAuthorB();
        underTest.save(author1);
        Author author2 = TestDataUtil.createTestAuthorC();
        underTest.save(author2);
        Author author3 = TestDataUtil.createTestAuthorD();
        underTest.save(author3);

        List<Author> result  = underTest.findAuthorsWithAgeGreaterThan(35);
        assertThat(result).containsExactly(author3);

    }






}
