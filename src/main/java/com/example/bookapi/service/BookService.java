package com.example.bookapi.service;
import com.example.bookapi.dto.BookDto;
import com.example.bookapi.entity.Book;
import com.example.bookapi.mapper.BookMapper;
import com.example.bookapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
@Service @RequiredArgsConstructor @Transactional
public class BookService {
  private final BookRepository repo; private final BookMapper m;
  public BookDto create(BookDto d){return m.toDto(repo.save(m.toEntity(d))); }
  public BookDto find(Long id){return m.toDto(repo.findById(id).orElseThrow());}
  public Page<BookDto> list(Pageable p){return repo.findAllByDeletedFalse(p).map(m::toDto);}
  public BookDto update(Long id,BookDto d){
    Book b=repo.findById(id).orElseThrow();
    b.setTitle(d.getTitle()); b.setPrice(d.getPrice()); b.setPublishedAt(d.getPublishedAt());
    return m.toDto(b);
  }
  public void delete(Long id){repo.delete(repo.findById(id).orElseThrow());}
}
