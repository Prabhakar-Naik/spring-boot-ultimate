package com.springboot.ultimate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.ultimate.TestDataUtil;
import com.springboot.ultimate.domain.dto.BookDto;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.entities.Book;
import com.springboot.ultimate.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author prabhakar, @Date 07-08-2024
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    private final BookService service;

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, BookService service){
        this.mockMvc = mockMvc;
        this.service = service;
        this.objectMapper = new ObjectMapper();
    }


    @Test
    public void testThatCreateBookSuccessfullyReturnedHttp201Created(){
        try {
            BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
            String bookJson = objectMapper.writeValueAsString(bookDto);

            mockMvc.perform(
                    MockMvcRequestBuilders.post("/books/createBook")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(bookJson)
            ).andExpect(
                    MockMvcResultMatchers.status().isCreated()
            );

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnedSaveAuthor() {
        try {

            BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);

            String bookDtoJson = objectMapper.writeValueAsString(bookDto);

            mockMvc.perform(
                            MockMvcRequestBuilders.post("/books/createBook")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(bookDtoJson)
                    ).andExpect(
                            MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
                    )
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
                    )
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$.authorId").value(bookDto.getAuthorId())
                    );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @Test
    public void testThatListBooksReturnsHttpStatus200(){
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.get("/books/getListOfBooks")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




    @Test
    public void testThatListBooksReturnsListOfBooks(){
        try {
            // if there is any issue with the database try below one
            //Book book = TestDataUtil.creatTestBookA(null);
            //this.service.createBook(book);


            mockMvc.perform(
                            MockMvcRequestBuilders.get("/books/getListOfBooks")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$[0].isbn").value("98-765-4323")    // isString()
                    ).andExpect(
                            MockMvcResultMatchers.jsonPath("$[0].title").value("This shadow in the Attic") // isString()
                    ).andExpect(
                            MockMvcResultMatchers.jsonPath("$[0].authorId").isNumber()  //value(book.getAuthorId())
                    );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testThatBookReturnsHttpStatus200WhenBookExist() {
        try {
            Book book = TestDataUtil.creatTestBookA(null);
            this.service.createBook(book);

            mockMvc.perform(
                            MockMvcRequestBuilders.get("/books/getOneBook/123-456-789")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // this is for failure case
    @Test
    public void testThatBookReturnsHttpStatus404WhenDoesNotBookExist() {
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.get("/books/getOneBook/123-456-78")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // this is for matching content
    @Test
    public void testThatAuthorReturnsAuthorWhenAuthorExist() {
        try {
            Book book = TestDataUtil.creatTestBookA(null);
            this.service.createBook(book);

            mockMvc.perform(
                            MockMvcRequestBuilders.get("/books/getOneBook/98-765-4323")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value("98-765-4323"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("This shadow in the Attic"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.authorId").value(book.getAuthorId())
                    );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testThatUpdateBookReturnsHttpStatus200Ok(){
        try {
            Book book = TestDataUtil.creatTestBookA(null);
            Book savedBook = this.service.updateBook(book);

            BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
            bookDto.setIsbn(savedBook.getIsbn());
            String bookJson = objectMapper.writeValueAsString(bookDto);

            mockMvc.perform(
                    MockMvcRequestBuilders.put("/books/updateBook/"+savedBook.getIsbn())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(bookJson)
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testThatCreateUpdateBookReturnsHttpStatus200OkOr201Created(){
        try {
            Book book = TestDataUtil.creatTestBookA(null);
            Book savedBook = this.service.createupdateBook(book.getIsbn(), book);

            BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
            bookDto.setIsbn(savedBook.getIsbn());
            String bookJson = objectMapper.writeValueAsString(bookDto);

            mockMvc.perform(
                    MockMvcRequestBuilders.post("/books/createUpdateBook/"+savedBook.getIsbn())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(bookJson)
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.isbn").value(savedBook.getIsbn())
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.authorId").value(bookDto.getAuthorId())
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void  testThatUpdateBookReturnUpdatedBook(){
        try {
            Book book = TestDataUtil.creatTestBookA(null);
            Book savedBook = this.service.createupdateBook(book.getIsbn(),book);

            BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
            bookDto.setIsbn(savedBook.getIsbn());
            bookDto.setTitle("UPDATED");
            String bookJson = objectMapper.writeValueAsString(bookDto);

            mockMvc.perform(
                    MockMvcRequestBuilders.post("/books/createUpdateBook/"+savedBook.getIsbn())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(bookJson)
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.isbn").value("98-765-4323")
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.title").value("This shadow in the Attic")
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.authorId").value(bookDto.getAuthorId())
            );

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



    @Test
    public void testThatPartialUpdateExistingBookReturnsHttpStatus200Ok(){
        try {
            Book book = TestDataUtil.creatTestBookA(null);
            this.service.createupdateBook(book.getIsbn(), book);

            BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
            bookDto.setTitle("UPDATED");
            String bookJson = objectMapper.writeValueAsString(bookDto);

            mockMvc.perform(
                    MockMvcRequestBuilders.patch("/books/partialUpdate/"+book.getIsbn())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(bookJson)
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
            );
        }catch (Exception e){
            System.out.println("e = " + e.getMessage());
        }
    }


    @Test
    public void testThatPartialUpdateExistingBookReturnsUpdatedBook(){
        try {
            Book book = TestDataUtil.creatTestBookA(null);
            this.service.createupdateBook(book.getIsbn(), book);

            BookDto bookDto = TestDataUtil.creatTestBookDtoA(null);
            bookDto.setTitle("UPDATED");
            String bookJson = objectMapper.writeValueAsString(bookDto);

            mockMvc.perform(
                    MockMvcRequestBuilders.patch("/books/partialUpdate/"+book.getIsbn())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(bookJson)
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn())
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.authorId").value(bookDto.getAuthorId())
            );
        }catch (Exception e){
            System.out.println("e = " + e.getMessage());
        }
    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus204OrNonContent() {
        try {
            Book book = TestDataUtil.creatTestBookA(null);
            Book savedBook = this.service.createupdateBook(book.getIsbn(),book);
            mockMvc.perform(
                    MockMvcRequestBuilders.delete("/books/deleteBook/"+savedBook.getIsbn())
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(
                    MockMvcResultMatchers.status().isNoContent()
            );
        }catch (Exception e){
            System.out.println("e = " + e.getMessage());
        }
    }






}
