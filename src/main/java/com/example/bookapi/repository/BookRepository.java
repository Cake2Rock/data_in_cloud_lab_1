package com.example.bookapi.repository;
import com.example.bookapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;
public interface BookRepository extends JpaRepository<Book,Long>{
  Page<Book> findAllByDeletedFalse(Pageable p);
}
