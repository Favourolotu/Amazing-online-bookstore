package project.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    @WithUserDetails("bing")
    public void defaultUserViewTest() throws Exception {
        mockMvc.perform(get("/user/bing"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("username"))
                .andExpect(content().string(containsString("<h1>User: bing</h1>")))
                .andExpect(view().name("user"));
    }



    @Test
    @WithUserDetails("bing")
    public void searchForBookTest() throws Exception {
        mockMvc.perform(get("/user/bing/search")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("bookName", String.valueOf("The Great Gatsby")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>User: bing</h1>")))
                .andExpect(view().name("list-books"));
    }

    @Test
    @WithUserDetails("bing")
    public void viewShoppingCartTest() throws Exception {
        mockMvc.perform(get("/user/bing/viewCart")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>User: bing</h1>")))
                .andExpect(view().name("view-cart"));
    }

    @Test
    @WithUserDetails("bing")
    public void makePurchaseTest() throws Exception {
        mockMvc.perform(post("/user/bing/makePurchase")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>User: bing</h1>")))
                .andExpect(view().name("user-purchases"));
    }
}
