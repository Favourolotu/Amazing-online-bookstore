package project;

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
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getOwnerPageTest() throws Exception {
        mockMvc.perform(get("/owner"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("inventories"))
                .andExpect(view().name("owner"));
    }

    @Test
    public void displayAddBookPageTest() throws Exception {
        mockMvc.perform(get("/owner/add")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("inventoryId", String.valueOf(1L)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("inventoryId"))
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("add-book"));
    }

    @Test
    public void addNewBookTest() throws Exception {
        mockMvc.perform(post("/owner/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("inventoryId", String.valueOf(1L))
                        .param("title", "test-title")
                        .param("author", "test-author")
                        .param("description", "test-description")
                        .param("publisher", "test-publisher"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/owner"));

    }

    @Test
    public void displayEditBookPageTest() throws Exception {
        mockMvc.perform(get("/owner/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("bookISBN", String.valueOf(1L)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("edit-book"));

    }

    @Test
    public void editBookTest() throws Exception {
        mockMvc.perform(post("/owner/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("bookISBN", String.valueOf(1L))
                        .param("title", "test-title")
                        .param("author", "test-author")
                        .param("description", "test-description")
                        .param("publisher", "test-publisher"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/owner"));

    }

}
