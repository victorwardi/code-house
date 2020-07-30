package run.victor.api.codehouse.controller;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;
import run.victor.api.codehouse.request.NewCategoryRequest;
import run.victor.api.codehouse.validator.ProhibitsDuplicateCategoryValidator;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Victor Wardi - @victorwardi
 */
@WebMvcTest(controllers = CategoriesController.class)
class CategoriesControllerTest {

    private static final String URL = "/v1/categories";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private ProhibitsDuplicateCategoryValidator prohibitsDuplicateAuthorEmailValidator;

    @BeforeEach
    void setUp() {
        when(prohibitsDuplicateAuthorEmailValidator.supports(any())).thenReturn(true);
    }

    @Test
    void whenCategoryValid_thenReturnsStatus200() throws Exception {
        NewCategoryRequest validaCategory = NewCategoryRequest.create("JAVA");
        String requestBody = objectMapper.writeValueAsString(validaCategory);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void whenCategoryNameNotInformed_thenReturnsStatus400() throws Exception {
        NewCategoryRequest validaCategory = NewCategoryRequest.create("");
        String requestBody = objectMapper.writeValueAsString(validaCategory);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("Name must be informed")));
    }

    @Test
    void whenCategoryNameDuplicated_thenReturnsStatus400() throws Exception {
        NewCategoryRequest validaCategory = NewCategoryRequest.create("JAVA");
        String requestBody = objectMapper.writeValueAsString(validaCategory);
        doNothing().when(prohibitsDuplicateAuthorEmailValidator).validate(any(), any(Errors.class));

        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("Name must be informed")));
    }




}
