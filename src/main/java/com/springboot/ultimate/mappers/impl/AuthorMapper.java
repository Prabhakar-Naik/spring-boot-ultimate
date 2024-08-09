package com.springboot.ultimate.mappers.impl;

import com.springboot.ultimate.domain.dto.AuthorDto;
import com.springboot.ultimate.domain.entities.Author;
import com.springboot.ultimate.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author prabhakar, @Date 07-08-2024
 */

@Service
public class AuthorMapper implements Mapper<Author, AuthorDto> {
    private final ModelMapper modelMapper;

    public AuthorMapper(ModelMapper mapper){
        this.modelMapper = mapper;
    }

    @Override
    public AuthorDto mapTo(Author author) {
        return modelMapper.map(author,AuthorDto.class);
    }

    @Override
    public Author mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
