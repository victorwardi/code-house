package run.victor.api.codehouse.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import run.victor.api.codehouse.repository.BookRepository;
import run.victor.api.codehouse.request.NewBookRequest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Victor Wardi - @victorwardi
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql("/data/books.sql")
@Sql(scripts = "/data/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BooksControllerIT {

    private static final String URL = "/v1/books";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    BookRepository bookRepository;

    @Test
    void whenBookValid_thenReturnsStatus200() throws Exception {
        NewBookRequest validBook = NewBookRequest.create("Java Development 2020", "All about Java", "sumary...",
            BigDecimal.valueOf(20.00), 123, "123-090-233-998", LocalDateTime.now().plusDays(1), 1L, 1L);
        String requestBody = objectMapper.writeValueAsString(validBook);

        mockMvc.perform(post(URL).contentType("application/json").content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void whenRequiredFieldsNotInformed_thenReturnsStatus400() throws Exception {

        NewBookRequest invalidBook = NewBookRequest.create(null, null, null,null, null, null, null, null, null);
        String requestBody = objectMapper.writeValueAsString(invalidBook);

        mockMvc.perform(post(URL).contentType("application/json").content(requestBody)).
            andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("\"title\":\"must not be blank\"")))
            .andExpect(content().string(containsString("\"description\":\"must not be blank\"")))
            .andExpect(content().string(containsString("\"price\":\"must not be null\"")))
            .andExpect(content().string(containsString("\"pages\":\"must not be null\"")))
            .andExpect(content().string(containsString("\"isbn\":\"must not be blank\"")))
            .andExpect(content().string(containsString("\"publishDate\":\"must not be null\"")))
            .andExpect(content().string(containsString("\"idCategory\":\"must not be null\"")))
            .andExpect(content().string(containsString("\"idAuthor\":\"must not be null\"")));
    }
    @Test
    void whenBookTitleDuplicate_thenReturnsStatus400() throws Exception {
        //Spring MVC is already on DB, so a duplicate name is not allowed
        NewBookRequest duplicatedTitleBook = NewBookRequest.create("Spring MVC", "All about Java", "sumary...",
            BigDecimal.valueOf(20.00), 123, "123-090-233-998", LocalDateTime.now().plusDays(1), 1L, 1L);
        String requestBody = objectMapper.writeValueAsString(duplicatedTitleBook);

        mockMvc.perform(post(URL).contentType("application/json").content(requestBody)).
             andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("\"title\":\"Field must be unique\"")));
    }

    @Test
    void whenBookISBNDuplicate_thenReturnsStatus400() throws Exception {
        //ISBN (978-85-5519-019-3) is already on DB, so a duplicate one is not allowed
        NewBookRequest duplicatedTitleBook = NewBookRequest.create("Spring MVC 2020", "All about Java", "sumary...",
            BigDecimal.valueOf(20.00), 123, "978-85-5519-019-3", LocalDateTime.now().plusDays(1), 1L, 1L);
        String requestBody = objectMapper.writeValueAsString(duplicatedTitleBook);

        mockMvc.perform(post(URL).contentType("application/json").content(requestBody)).
            andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("\"isbn\":\"Field must be unique\"")));
    }

    @Test
    void whenBookCategoryDoesNotExist_thenReturnsStatus400() throws Exception {
        NewBookRequest duplicatedTitleBook = NewBookRequest.create("Spring MVC 2020", "All about Java", "sumary...",
            BigDecimal.valueOf(20.00), 123, "978-85-5519-019-3", LocalDateTime.now().plusDays(1), 8383L, 1L);
        String requestBody = objectMapper.writeValueAsString(duplicatedTitleBook);

        mockMvc.perform(post(URL).contentType("application/json").content(requestBody)).
            andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("\"idCategory\":\"Field must exist on database\"")));
    }

    @Test
    void whenBookAuthorDoesNotExist_thenReturnsStatus400() throws Exception {
        NewBookRequest duplicatedTitleBook = NewBookRequest.create("Spring MVC 2020", "All about Java", "sumary...",
            BigDecimal.valueOf(20.00), 123, "978-85-5519-019-3", LocalDateTime.now().plusDays(1), 1L, 109L);
        String requestBody = objectMapper.writeValueAsString(duplicatedTitleBook);

        mockMvc.perform(post(URL).contentType("application/json").content(requestBody)).
            andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("\"idAuthor\":\"Field must exist on database\"")));
    }

}