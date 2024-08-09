package com.springboot.ultimate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.entities.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author prabhakar, @Date 06-08-2024
 */
public class JacksonTests {

    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Create an example Author
        Author author = new Author();
        author.setId(1L);
        author.setName("prashanth");
        author.setAge(45);

        // Create an example Book
        Book book = new Book();
        book.setIsbn("123-456-789");
        book.setTitle("This is My Life");
        book.setAuthorId(author);

//        Author author = new Author(1L,"prashanth",45);
//         Book book = Book.builder()
//                .isbn("123-456-789")
//                .title("This is My Life")
//                .authorId(author)
//                .build();
         String result = objectMapper.writeValueAsString(book);

         assertThat(result).isEqualTo("{\"isbn\":\"123-456-789\",\"title1\":\"This is My Life\",\"authorId\":{\"id\":1,\"name\":\"prashanth\",\"age\":45}}");
    }


    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() throws JsonProcessingException {
        String json = "{\"isbn\":\"123-456-789\",\"title1\":\"This is My Life\",\"authorId\":{\"id\":1,\"name\":\"prashanth\",\"age\":45}}";
        Author author = new Author(1L,"prashanth",45);
        Book book = Book.builder()
                .isbn("123-456-789")
                .title("This is My Life")
                .authorId(author)
                .build();

        final ObjectMapper objectMapper = new ObjectMapper();

        Book result = objectMapper.readValue(json,Book.class);
        assertThat(result).isEqualTo(book);
    }



}
