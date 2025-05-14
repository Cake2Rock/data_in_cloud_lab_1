package com.example.bookapi.controller;
import com.example.bookapi.dto.BookDto;
import com.example.bookapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/books") @RequiredArgsConstructor @Validated
public class BookController {
  private final BookService s;
  @GetMapping("/{id}") public BookDto one(@PathVariable Long id){return s.find(id);}
  @GetMapping public Page<BookDto> all(@RequestParam(defaultValue="0") int page,
                                       @RequestParam(defaultValue="10") int size){
    return s.list(PageRequest.of(page,size,Sort.by("id"))); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED)
  public BookDto create(@RequestBody @Validated BookDto d){return s.create(d);}
  @PutMapping("/{id}") public BookDto update(@PathVariable Long id,@RequestBody @Validated BookDto d){
    return s.update(id,d);}
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){s.delete(id);}
}
