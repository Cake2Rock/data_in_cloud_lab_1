package com.example.bookapi.mapper;
import com.example.bookapi.dto.BookDto;
import com.example.bookapi.entity.Book;
import org.mapstruct.Mapper;
@Mapper(componentModel="spring")
public interface BookMapper {
  BookDto toDto(Book e);
  Book toEntity(BookDto d);
}
