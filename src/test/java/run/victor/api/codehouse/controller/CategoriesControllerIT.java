package run.victor.api.codehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import run.victor.api.codehouse.repository.CategoryRepository;
import run.victor.api.codehouse.request.NewCategoryRequest;

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
@Transactional
class CategoriesControllerIT {

    private static final String URL = "/v1/categories";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void whenCategoryValid_thenReturnsStatus200() throws Exception {
        NewCategoryRequest validaCategory = NewCategoryRequest.create("Development");
        String requestBody = objectMapper.writeValueAsString(validaCategory);
        mockMvc.perform(post(URL).contentType("application/json")
            .content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void whenCategoryNameDuplicated_thenReturnsStatus400() throws Exception {
        //Java category is already registered on DB.
        NewCategoryRequest validaCategory = NewCategoryRequest.create("JAVA");
        String requestBody = objectMapper.writeValueAsString(validaCategory);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("must be unique")));
    }

    @Test
    void whenCategoryNameNotInformed_thenReturnsStatus400() throws Exception {
        NewCategoryRequest validaCategory = NewCategoryRequest.create("");
        String requestBody = objectMapper.writeValueAsString(validaCategory);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("must not be blank")));
    }
}
