package com.springboot.ultimate.domain;

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
public class Author {

    private Long id;
    private String name;
    private Integer age;
}
