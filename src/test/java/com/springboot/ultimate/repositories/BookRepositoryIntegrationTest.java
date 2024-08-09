package com.springboot.ultimate.repositories;

import com.springboot.ultimate.TestDataUtil;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.entities.Book;
import com.springboot.ultimate.repository.AuthorRepository;
import com.springboot.ultimate.repository.BookRepository;
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
public class BookRepositoryIntegrationTest {

    private final AuthorRepository authorDAO;
    private final BookRepository underTest;


    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest, AuthorRepository authorDAO) {
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }

    @Test
    public void testThatBookCanBeCreatedAndCalled(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.creatTestBookA(author);

        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedRecalled(){
        Author author = TestDataUtil.createTestAuthorA();

        Book book = TestDataUtil.creatTestBookA(author);
        underTest.save(book);

        Book book1 = TestDataUtil.creatTestBookB(author);
        underTest.save(book1);

        Book book2 = TestDataUtil.creatTestBookC(author);
        underTest.save(book2);

        Book book3 = TestDataUtil.creatTestBookD(author);
        underTest.save(book3);

        List<Book> result = underTest.findAll();

        assertThat(result)
                .hasSize(4)
                .containsExactly(book,book1,book2,book3);

    }


    @Test
    public void testThatBookCanUpdated(){

        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.save(author);

        Book book = TestDataUtil.creatTestBookA(author);
        underTest.save(book);

        book.setTitle("UPDATED");
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }


    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.save(author);

        Book book = TestDataUtil.creatTestBookA(author);
        underTest.save(book);

        underTest.deleteById(book.getIsbn());

        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isEmpty();
    }


    @Test
    public void testThatBooksCanBeDeletedAll(){
         underTest.deleteAll();
    }




}
