package kr.ac.jejunu.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTests {
    @Autowired
    MockMvc mvc;

    @MockBean
    private TodoController todoController;

    @Test
    public void todo() throws Exception {
        List<Todo> todos = new ArrayList<>();
        int id = 1;
        String text = "Spring 공부하기";
        Todo todo = Todo.builder().id(id).text(text).build();
        todos.add(todo);

        given(todoController.todo()).willReturn(todos);
        mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(id)))
                .andExpect(jsonPath("$[0].todo", is(text)));
    }
}
