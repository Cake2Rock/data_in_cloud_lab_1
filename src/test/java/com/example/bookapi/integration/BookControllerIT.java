package com.example.bookapi.integration;
import com.example.bookapi.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BookControllerIT {
  @Container static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:16");
  @DynamicPropertySource static void cfg(DynamicPropertyRegistry r){
    r.add("spring.datasource.url",db::getJdbcUrl);
    r.add("spring.datasource.username",db::getUsername);
    r.add("spring.datasource.password",db::getPassword);
  }
  @Autowired MockMvc mvc; @Autowired ObjectMapper json;
  @Test void createAndGet() throws Exception{
    BookDto dto=new BookDto(null,"Clean Code",new BigDecimal("20.5"),LocalDate.of(2008,8,1));
    String body=mvc.perform(post("/api/v1/books").contentType(APPLICATION_JSON)
                .content(json.writeValueAsString(dto)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
    Long id=json.readValue(body,BookDto.class).getId();
    mvc.perform(get("/api/v1/books/{id}",id)).andExpect(status().isOk())
       .andExpect(jsonPath("$.title").value("Clean Code"));
  }
}
