package project.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void defaultDisplayTest() throws Exception {
        mockMvc.perform(get("/userDefaultPage"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("inventories"))
                .andExpect(view().name("user-default-page"));
    }



    @Test
    public void searchForBookTest() throws Exception {
        mockMvc.perform(post("/searchForBook")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("bookName", String.valueOf("The Great Gatsby")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("list-of-books-display"));
    }

    @Test
    public void logoutTest() throws Exception {
        mockMvc.perform(post("/logout")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login-page"));
    }

    @Test
    public void viewShoppingCartTest() throws Exception {
        mockMvc.perform(get("/viewShoppingCart")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("view-shopping-cart"));
    }

    @Test
    public void makePurchaseTest() throws Exception {
        mockMvc.perform(post("/makePurchase")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user-purchases"));
    }
}
