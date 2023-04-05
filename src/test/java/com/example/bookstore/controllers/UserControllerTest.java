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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails()
    public void viewShoppingCartTest() throws Exception {
        mockMvc.perform(get("/user/cart")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("username", "user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cart"))
                .andExpect(content().string(containsString("<h1>Shopping Cart</h1>")))
                .andExpect(view().name("cart"));
    }

    @Test
    @WithUserDetails()
    public void addToCartTest() throws Exception {
        mockMvc.perform(post("/user/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "user")
                        .param("bookISBN", String.valueOf(1))
                        .param("quantity", String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/cart?username=user"));
    }

    @Test
    @WithUserDetails()
    public void removeFromCartTest() throws Exception {
        mockMvc.perform(post("/user/remove")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "user")
                        .param("bookISBN", String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/cart?username=user"));
    }

    @Test
    @WithUserDetails()
    public void checkoutTest() throws Exception {
        mockMvc.perform(post("/user/checkout")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "user"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/cart?username=user"));
    }

    @Test
    @WithUserDetails()
    public void viewRecommendationsTest() throws Exception {
        mockMvc.perform(get("/user/recommendations")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(view().name("home"));
    }

}
