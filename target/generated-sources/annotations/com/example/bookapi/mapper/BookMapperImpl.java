package com.example.bookapi.mapper;

import com.example.bookapi.dto.BookDto;
import com.example.bookapi.entity.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T20:37:46+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toDto(Book e) {
        if ( e == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( e.getId() );
        bookDto.setPrice( e.getPrice() );
        bookDto.setPublishedAt( e.getPublishedAt() );
        bookDto.setTitle( e.getTitle() );

        return bookDto;
    }

    @Override
    public Book toEntity(BookDto d) {
        if ( d == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( d.getId() );
        book.setPrice( d.getPrice() );
        book.setPublishedAt( d.getPublishedAt() );
        book.setTitle( d.getTitle() );

        return book;
    }
}
