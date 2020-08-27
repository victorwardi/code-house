package run.victor.api.codehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import run.victor.api.codehouse.request.NewStateRequest;

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
class StatesControllerIT {

    private static final String URL = "/v1/states";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void whenStateValid_thenReturnsStatus200() throws Exception {
        NewStateRequest validState = new NewStateRequest("Bahia", 1L);
        String requestBody = objectMapper.writeValueAsString(validState);
        mockMvc.perform(post(URL).contentType("application/json")
            .content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void whenStateNameNotInformed_thenReturnsStatus400() throws Exception {
        NewStateRequest stateWithoutName = new NewStateRequest("", 1L);
        String requestBody = objectMapper.writeValueAsString(stateWithoutName);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("must not be blank")));
    }

    @Test
    void whenStateNameDuplicated_thenReturnsStatus400() throws Exception {
        //Rio de Janeiro state is already registered on DB.
        NewStateRequest duplicatedState = new NewStateRequest("Rio de Janeiro", 1L);
        String requestBody = objectMapper.writeValueAsString(duplicatedState);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("must be unique")));
    }

    @Test
    void whenCountryIdNull_thenReturnsStatus400() throws Exception {
        NewStateRequest stateWithoutCountry = new NewStateRequest("Lisbon", null);
        String requestBody = objectMapper.writeValueAsString(stateWithoutCountry);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("\"countryId\":\"must not be null\"")));
    }

    @Test
    void whenCountryDoesNotExist_thenReturnsStatus400() throws Exception {
        NewStateRequest stateWithoutCountry = new NewStateRequest("Paris", 5L);
        String requestBody = objectMapper.writeValueAsString(stateWithoutCountry);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("{\"countryId\":\"Field must exist on database\"}")));
    }



}