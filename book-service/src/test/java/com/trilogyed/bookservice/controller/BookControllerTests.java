package com.trilogyed.bookservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = BookController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetRoute() throws Exception {
        this.mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @Test
    public void postBook() throws Exception {
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("J.K. Rowling");
        this.mockMvc.perform(post("/books")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());
    }

}
