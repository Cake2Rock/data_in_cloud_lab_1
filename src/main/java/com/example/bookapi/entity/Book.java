package com.example.bookapi.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
@Entity @Table(name="books")
@SQLDelete(sql="UPDATE books SET deleted=true WHERE id=?")
@Where(clause="deleted=false")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Book {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @NotBlank @Size(max=120) private String title;
  @DecimalMin("0.01") private BigDecimal price;
  @PastOrPresent private LocalDate publishedAt;
  private boolean deleted=false;
}
