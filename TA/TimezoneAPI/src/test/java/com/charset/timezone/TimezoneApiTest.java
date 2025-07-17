package com.charset.timezone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TimezoneApiTest {
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext context;
    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testTimeZoneAPI_1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/time/US:Panama")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.abbreviation").value("EST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.timezone").value("America/Panama"));
    }

    @Test
    public void testTimeZoneAPI_2() throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String formattedDate = zonedDateTime.toString();
        mockMvc.perform(MockMvcRequestBuilders.get("/time/EN:Panama")).andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testTimeZoneAPI_3() throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String formattedDate = zonedDateTime.toString();
        mockMvc.perform(MockMvcRequestBuilders.get("/time/US:Panama123")).andDo(print())
                .andExpect(status().isBadRequest());
    }










}
