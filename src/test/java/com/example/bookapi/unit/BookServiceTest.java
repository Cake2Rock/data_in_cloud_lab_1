package com.example.bookapi.unit;
import com.example.bookapi.dto.BookDto;
import com.example.bookapi.entity.Book;
import com.example.bookapi.mapper.BookMapper;
import com.example.bookapi.repository.BookRepository;
import com.example.bookapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
  @Mock BookRepository repo; @Mock BookMapper mapper; @InjectMocks BookService svc;
  @Test void createDelegatesToRepo(){
    BookDto dto=new BookDto(null,"A",BigDecimal.ONE,LocalDate.now());
    Book entity=new Book(1L,"A",BigDecimal.ONE,LocalDate.now(),false);
    when(mapper.toEntity(dto)).thenReturn(entity);
    when(repo.save(entity)).thenReturn(entity);
    when(mapper.toDto(entity)).thenReturn(new BookDto(1L,"A",BigDecimal.ONE,LocalDate.now()));
    BookDto saved=svc.create(dto);
    assertEquals(1L,saved.getId());
    verify(repo).save(entity);
  }
}
