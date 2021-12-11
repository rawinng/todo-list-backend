package com.rawinng.todolistbackend.controller;

import com.rawinng.todolistbackend.entity.Todo;
import com.rawinng.todolistbackend.repo.TodoRepo;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoRepo todoRepo;

    @Test
    public void test_GET_todo_shouldBeSuccess() throws Exception {
        Todo todo1 = new Todo("1", "Test Todo", false);
        Todo todo2 = new Todo("2", "Hello", false);

        when(todoRepo.findAll()).thenReturn(Arrays.asList(todo1, todo2));

        mockMvc.perform(get("/todo"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].id", is("1")),
                        jsonPath("$[0].title", is("Test Todo")),
                        jsonPath("$[0].completed", is(false)),
                        jsonPath("$[1].id", is("2")),
                        jsonPath("$[1].title", is("Hello")),
                        jsonPath("$[1].completed", is(false))
                );
    }

    @Test
    public void test_POST_todo_shouldBeSuccess() throws Exception {
        Todo todo = new Todo("1", "Hello Buddies", true);

        when(todoRepo.save(any())).thenReturn(todo);

        mockMvc.perform(post("/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Hello Buddies\",\"completed\":true}")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id", is("1")),
                        jsonPath("$.title", is("Hello Buddies")),
                        jsonPath("$.completed", is(true))
                );
    }
}
