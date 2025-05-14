package com.example.bookapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor         
@AllArgsConstructor        
public class BookDto {
  private Long id;
  @NotBlank @Size(max = 120) private String title;
  @DecimalMin("0.01")        private BigDecimal price;
  @PastOrPresent             private LocalDate publishedAt;
}
