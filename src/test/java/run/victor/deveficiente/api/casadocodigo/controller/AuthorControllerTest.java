package run.victor.deveficiente.api.casadocodigo.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import run.victor.deveficiente.api.casadocodigo.model.Author;
import run.victor.deveficiente.api.casadocodigo.repository.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Victor Wardi - @victorwardi
 */
@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

    private static final String URL = "/v1/authors";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuthorRepository authorRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void whenAuthorNameInvalid_thenReturnsStatus400() throws Exception {

        Author authorWithoutName = Author.builder()
            .email("bart@springfield.fox")
            .description("Random description")
            .build();

        String requestBody = objectMapper.writeValueAsString(authorWithoutName);

        mockMvc.perform(post(URL)
            .contentType("application/json")
            .content(requestBody))
            .andExpect(status().isBadRequest());
    }

}