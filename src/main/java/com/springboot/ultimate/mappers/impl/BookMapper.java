package com.springboot.ultimate.mappers.impl;

import com.springboot.ultimate.domain.dto.BookDto;
import com.springboot.ultimate.domain.entities.Book;
import com.springboot.ultimate.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author prabhakar, @Date 07-08-2024
 */
@Service
public class BookMapper implements Mapper<Book, BookDto> {

    private final ModelMapper modelMapper;

    public BookMapper(ModelMapper mapper){
        this.modelMapper = mapper;
    }

    @Override
    public BookDto mapTo(Book book) {
        return this.modelMapper.map(book, BookDto.class);
    }

    @Override
    public Book mapFrom(BookDto bookDto) {
        return this.modelMapper.map(bookDto, Book.class);
    }
}
