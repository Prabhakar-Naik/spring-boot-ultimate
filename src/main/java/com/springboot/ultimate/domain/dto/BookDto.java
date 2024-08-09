package com.springboot.ultimate.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springboot.ultimate.domain.entities.Author;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author prabhakar, @Date 07-08-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonPropertyOrder({ "isbn", "title1", "authorId" })
public class BookDto {

    private String isbn;
//    @JsonProperty("title1")
    private String title;
    private AuthorDto authorId;
}
