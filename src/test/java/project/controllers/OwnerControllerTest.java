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
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("owner")
    public void getOwnerPageTest() throws Exception {
        mockMvc.perform(get("/owner/owner"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(content().string(containsString("<h1>Owner 1 View</h1>")))
                .andExpect(view().name("owner"));
    }

    @Test
    @WithUserDetails("owner")
    public void displayAddBookPageTest() throws Exception {
        mockMvc.perform(get("/owner/owner/add")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("username", "owner"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("book"))
                .andExpect(content().string(containsString("<h1>Add Book View</h1>")))
                .andExpect(view().name("add-book"));
    }

    @Test
    @WithUserDetails("owner")
    public void displayEditBookPageTest() throws Exception {
        mockMvc.perform(get("/owner/owner/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("bookISBN", String.valueOf(1L)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(content().string(containsString("<h1>Edit Book #1</h1>")))
                .andExpect(view().name("edit-book"));

    }

    @Test
    @WithUserDetails("owner")
    public void addNewBookTest() throws Exception {
        mockMvc.perform(post("/owner/owner/add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "owner")
                        .param("title", "test-title")
                        .param("author", "test-author")
                        .param("description", "test-description")
                        .param("publisher", "test-publisher")
                        .param("stock", "0"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/owner/owner"));

    }

    @Test
    @WithUserDetails("owner")
    public void editBookTest() throws Exception {
        mockMvc.perform(post("/owner/owner/edit")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("bookISBN", String.valueOf(1L))
                        .param("title", "test-title")
                        .param("author", "test-author")
                        .param("description", "test-description")
                        .param("publisher", "test-publisher"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/owner/owner"));

    }

}
