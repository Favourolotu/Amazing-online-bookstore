package com.example.bookstore.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("owner")
    public void addBookPageTest() throws Exception {
        mockMvc.perform(get("/owner/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(content().string(containsString("<h1>Add Book View</h1>")))
                .andExpect(view().name("add-book"));
    }

    @Test
    @WithUserDetails("owner")
    public void addBookTest() throws Exception {
        mockMvc.perform(post("/owner/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "test-title")
                        .param("author", "test-author")
                        .param("description", "test-description")
                        .param("publisher", "test-publisher")
                        .param("stock", "0"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    @WithUserDetails("owner")
    public void editBookPageTest() throws Exception {
        mockMvc.perform(get("/owner/edit")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("bookISBN", String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(content().string(containsString("<h1>Edit Book")))
                .andExpect(view().name("edit-book"));
    }

    @Test
    @WithUserDetails("owner")
    public void editBookTest() throws Exception {
        mockMvc.perform(post("/owner/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("bookISBN", String.valueOf(1))
                        .param("title", "test-title")
                        .param("author", "test-author")
                        .param("description", "test-description")
                        .param("publisher", "test-publisher")
                        .param("stock", String.valueOf(5)))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/home"));
    }



}
