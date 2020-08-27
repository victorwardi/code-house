package run.victor.api.codehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import run.victor.api.codehouse.request.NewCountryRequest;

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
class CountriesControllerIT {

    private static final String URL = "/v1/countries";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void whenCountryValid_thenReturnsStatus200() throws Exception {
        final NewCountryRequest validCountry = new NewCountryRequest("Japan");
        String requestBody = objectMapper.writeValueAsString(validCountry);
        mockMvc.perform(post(URL).contentType("application/json")
            .content(requestBody)).andExpect(status().isOk());
    }

    @Test
    void whenCountryNameDuplicated_thenReturnsStatus400() throws Exception {
        //Brazil country is already registered on DB.
        final NewCountryRequest countryDuplicated = new NewCountryRequest("Brazil");
        String requestBody = objectMapper.writeValueAsString(countryDuplicated);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("must be unique")));
    }

    @Test
    void whenCategoryNameNotInformed_thenReturnsStatus400() throws Exception {
        final NewCountryRequest countryWithBlankName = new NewCountryRequest("");
        String requestBody = objectMapper.writeValueAsString(countryWithBlankName);
        mockMvc.perform(post(URL).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("must not be blank")));
    }
}
