package com.springboot.ultimate.repositories;

import com.springboot.ultimate.TestDataUtil;
import com.springboot.ultimate.dao.impl.AuthorDAOImpl;
import com.springboot.ultimate.dao.impl.BookDAOImpl;
import com.springboot.ultimate.domain.Author;
import com.springboot.ultimate.domain.Book;
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
public class BookDAOImplIntegrationTest {

    private final AuthorDAOImpl authorDAO;
    private final BookDAOImpl underTest;


    @Autowired
    public BookDAOImplIntegrationTest(BookDAOImpl underTest, AuthorDAOImpl authorDAO) {
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }

    @Test
    public void testThatBookCanBeCreatedAndCalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.create(author);
        Book book = TestDataUtil.creatTestBookA();
        book.setAuthorId(author.getId());

        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.create(author);

        Book book = TestDataUtil.creatTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);

        Book book1 = TestDataUtil.creatTestBookB();
        book.setAuthorId(author.getId());
        underTest.create(book1);

        Book book2 = TestDataUtil.creatTestBookC();
        book.setAuthorId(author.getId());
        underTest.create(book2);

        Book book3 = TestDataUtil.creatTestBookD();
        book.setAuthorId(author.getId());
        underTest.create(book3);

        List<Book> result = underTest.find();

        assertThat(result)
                .hasSize(4)
                .containsExactly(book,book1,book2,book3);

    }


    @Test
    public void testThatBookCanUpdated(){

        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.create(author);

        Book book = TestDataUtil.creatTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);

        book.setTitle("UPDATED");
        underTest.update(book.getIsbn(),book);

        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }


    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDAO.create(author);

        Book book = TestDataUtil.creatTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);

        underTest.deleteOne(book.getIsbn());

        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatBooksCanBeDeletedAll(){
         underTest.delete();
    }




}
