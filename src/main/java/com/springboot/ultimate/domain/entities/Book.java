package com.springboot.ultimate.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springboot.ultimate.domain.entities.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author prabhakar, @Date 29-07-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "isbn", "title1", "authorId" })
public class Book {
    @Id
    private String isbn;
    @JsonProperty("title1")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author authorId;
// Jdbc api
//    private Long authorId;
}
