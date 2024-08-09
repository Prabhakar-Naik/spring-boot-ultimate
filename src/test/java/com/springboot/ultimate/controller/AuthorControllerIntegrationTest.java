package com.springboot.ultimate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.ultimate.TestDataUtil;
import com.springboot.ultimate.domain.dto.AuthorDto;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.service.AuthorService;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

    private final AuthorService service;
    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;


    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, AuthorService service) {
        this.mockMvc = mockMvc;
        this.service = service;
        this.objectMapper = new ObjectMapper();
    }


    // this is creation status
    @Test
    public void testThatCreateAuthorSuccessfullyReturnedHttp201Created() {
        try {

            Author author = TestDataUtil.createTestAuthorA();
            author.setId(null);
            String authorJson = objectMapper.writeValueAsString(author);

            mockMvc.perform(
                    MockMvcRequestBuilders.post("/authors/createAuthor")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(authorJson)
            ).andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    // this is for saved in db
    @Test
    public void testThatCreateAuthorSuccessfullyReturnedSaveAuthor() {
        try {

            Author author = TestDataUtil.createTestAuthorA();
            author.setId(null);
            String authorJson = objectMapper.writeValueAsString(author);

            mockMvc.perform(
                            MockMvcRequestBuilders.post("/authors/createAuthor")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(authorJson)
                    ).andExpect(
                            MockMvcResultMatchers.jsonPath("$.id").isNumber()
                    )
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$.name").value("prabhakar") // or isString();
                    )
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$.age").value(24) // or isNumber();
                    );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // this is for status
    @Test
    public void testThatListAuthorsReturnsHttpStatus200() {
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.get("/authors/getListOfAuthors")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // this is for matching content
    @Test
    public void testThatListAuthorsReturnsListOfAuthors() {
        try {
            Author author = TestDataUtil.createTestAuthorA();
            this.service.createAuthor(author);

            mockMvc.perform(
                            MockMvcRequestBuilders.get("/authors/getListOfAuthors")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$[0].name").value("prabhakar") // or isString();
                    )
                    .andExpect(
                            MockMvcResultMatchers.jsonPath("$[0].age").value(24) // or isNumber();
                    );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // this is for status as well store in db
    @Test
    public void testThatAuthorReturnsHttpStatus200WhenAuthorExist() {
        try {
            Author author = TestDataUtil.createTestAuthorA();
            this.service.createAuthor(author);

            mockMvc.perform(
                            MockMvcRequestBuilders.get("/authors/getOneAuthor/1")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // this is for failure case
    @Test
    public void testThatAuthorReturnsHttpStatus404WhenNoAuthorExist() {
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders.get("/authors/getOneAuthor/75")
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
            Author author = TestDataUtil.createTestAuthorA();
            this.service.createAuthor(author);

            mockMvc.perform(
                            MockMvcRequestBuilders.get("/authors/getOneAuthor/1")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("prabhakar"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(24)
                    );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // this is for status as well store in db
    @Test
    public void testThatUpdateAuthorReturnsHttpStatus200WhenAuthorExist() {
        try {
            Author author = TestDataUtil.createTestAuthorA();
            Author author1 = this.service.createAuthor(author);

            AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
            String authorDtoJson = this.objectMapper.writeValueAsString(authorDto);

            mockMvc.perform(
                            MockMvcRequestBuilders.put("/authors/updateAuthor/" + author1.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(authorDtoJson))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // this is for failure case
    @Test
    public void testThatUpdateAuthorReturnsHttpStatus404WhenNoAuthorExist() {
        try {
            AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
            String authorDtoJson = this.objectMapper.writeValueAsString(authorDto);

            mockMvc.perform(
                            MockMvcRequestBuilders.put("/authors/updateAuthor/73")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(authorDtoJson))
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testThatUpdateAuthorUpdatesExistingAuthor(){
        try{
            Author author = TestDataUtil.createTestAuthorA();
            Author author1 = this.service.createAuthor(author);

            Author existAuthor = TestDataUtil.createTestAuthorB();
            existAuthor.setId(author1.getId());

            String authorDtoJson = this.objectMapper.writeValueAsString(existAuthor);

            mockMvc.perform(
                            MockMvcRequestBuilders.put("/authors/updateAuthor/"+author1.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(authorDtoJson))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(author1.getId()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(existAuthor.getName()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(existAuthor.getAge())
                    );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus200Ok(){
        try {
            Author author = TestDataUtil.createTestAuthorA();
            Author savedAuthor = this.service.createAuthor(author);

            AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
            authorDto.setName("UPDATED");
            String authorDtoJson = objectMapper.writeValueAsString(authorDto);

            mockMvc.perform(
                    MockMvcRequestBuilders.patch("/authors/partialUpdate/"+savedAuthor.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(authorDtoJson)
            ).andExpect(MockMvcResultMatchers.status().isOk());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor(){
        try {
            Author author = TestDataUtil.createTestAuthorA();
            Author savedAuthor = this.service.createAuthor(author);

            AuthorDto authorDto = TestDataUtil.createTestAuthorDtoA();
            authorDto.setName("UPDATED");
            String authorDtoJson = objectMapper.writeValueAsString(authorDto);

            mockMvc.perform(
                    MockMvcRequestBuilders.patch("/authors/partialUpdate/"+savedAuthor.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(authorDtoJson)
            ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("UPDATED"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
                    );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204OrNoContent() {
        try {
            Author author = TestDataUtil.createTestAuthorA();
            Author savedAuthor = this.service.createAuthor(author);

            mockMvc.perform(
                    MockMvcRequestBuilders.delete("/authors/deleteAuthor/"+savedAuthor.getId())
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(
                    MockMvcResultMatchers.status().isNoContent()
            );
        }catch (Exception e){
            System.out.println("e = " + e.getMessage());
        }
    }

}
