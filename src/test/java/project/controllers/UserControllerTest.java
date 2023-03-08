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
                .andExpect(view().name("userDefaultPage"));
    }



    @Test
    public void searchForBookTest() throws Exception {
        mockMvc.perform(post("/searchForBook")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("bookName", String.valueOf("The Great Gatsby")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("listOfBooksDisplay"));
    }


}
