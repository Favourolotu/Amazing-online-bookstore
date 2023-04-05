package com.example.bookstore.controllers;

import com.example.bookstore.user.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void registerTest() throws Exception {
        mockMvc.perform(post("/login/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "new-user")
                        .param("password", "pass")
                        .param("role", String.valueOf(Role.USER)))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));

    }

    @Test
    public void loginPageTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>Sign in</h1>")))
                .andExpect(view().name("login"));
    }

    @Test
    public void registerPageTest() throws Exception {
        mockMvc.perform(get("/login/register"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("registerRequest"))
                .andExpect(content().string(containsString("<h1>Sign up</h1>")))
                .andExpect(view().name("register"));
    }
}
