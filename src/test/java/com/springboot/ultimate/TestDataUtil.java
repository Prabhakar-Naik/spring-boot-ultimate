package com.springboot.ultimate;

import com.springboot.ultimate.domain.dto.AuthorDto;
import com.springboot.ultimate.domain.dto.BookDto;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.domain.entities.Book;

/**
 * @author prabhakar, @Date 30-07-2024
 */
public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("prabhakar")
                .age(24)
                .build();
    }

    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()
                .id(1L)
                .name("prabhakar")
                .age(24)
                .build();
    }
    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("sudhakar")
                .age(29)
                .build();
    }
    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("veera")
                .age(30)
                .build();
    }
    public static Author createTestAuthorD() {
        return Author.builder()
                .id(4L)
                .name("kiran")
                .age(40)
                .build();
    }

    // here i modified parameter as final Author author
    // as well .authorId(author) this one for data jpa dependency requirement.

    public static Book creatTestBookA(final Author author) {
        return Book.builder()
                .isbn("98-765-43237")
                .title("This shadow")
                .authorId(author)
                .build();
    }

    public static BookDto creatTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("98-765-4323")
                .title("This shadow in the Attic")
                .authorId(author)
                .build();
    }

    public static Book creatTestBookB(final Author author) {
        return Book.builder()
                .isbn("98-765-433")
                .title("This shadow in the Attic")
                .authorId(author)
                .build();
    }

    public static Book creatTestBookC(final Author author) {
        return Book.builder()
                .isbn("98-765-434")
                .title("This shadow")
                .authorId(author)
                .build();
    }

    public static Book creatTestBookD(final Author author) {
        return Book.builder()
                .isbn("98-765-435")
                .title("This shadow")
                .authorId(author)
                .build();
    }
}
