package com.springboot.ultimate;

import com.springboot.ultimate.domain.Author;
import com.springboot.ultimate.domain.Book;

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

    public static Book creatTestBookA() {
        return Book.builder()
                .isbn("98-765-4323")
                .title("This shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static Book creatTestBookB() {
        return Book.builder()
                .isbn("98-765-433")
                .title("This shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static Book creatTestBookC() {
        return Book.builder()
                .isbn("98-765-434")
                .title("This shadow")
                .authorId(1L)
                .build();
    }

    public static Book creatTestBookD() {
        return Book.builder()
                .isbn("98-765-435")
                .title("This shadow")
                .authorId(1L)
                .build();
    }
}
