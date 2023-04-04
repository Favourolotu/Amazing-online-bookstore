package com.example.bookstore.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithUserDetails()
    public void homeTest() throws Exception {
        mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(content().string(containsString("<h1>Welcome to the Amazing Bookstore</h1>")))
                .andExpect(view().name("home"));
    }

    @Test
    @WithUserDetails()
    public void searchTest() throws Exception {
        mockMvc.perform(get("/home/search")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("query", "Scott"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(content().string(containsString("<h5>The Great Gatsby</h5>")))
                .andExpect(view().name("home"));

    }

    @Test
    @WithUserDetails()
    public void sortTest() throws Exception {
        mockMvc.perform(get("/home/sort")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("query", "author"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(content().string(containsString("<h5>Brave New World</h5>")))
                .andExpect(view().name("home"));

    }


}
