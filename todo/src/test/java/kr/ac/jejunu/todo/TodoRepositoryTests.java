package kr.ac.jejunu.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

@DataJpaTest
public class TodoRepositoryTests {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void findAll() {
        String text = "Spring 공부하기";
        Boolean isDone = false;
        Todo todo = Todo.builder().text(text).isDone(isDone).build();
        entityManager.persist(todo);
        List<Todo> todos = todoRepository.findAll();
        assertThat(todos.get(0).getId(), greaterThan(0));
        assertThat(todos.get(0).getText(), is(text));
        assertThat(todos.get(0).getIsDone(), is(isDone));
    }
}
