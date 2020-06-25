package kr.ac.jejunu.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor // final에 대한 객체에 생명력을 넣어줌.
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping("/todo")
    public Result<List<Todo>> todo() {
        return new Result(todoRepository.findAll());
    }

    @PostMapping("/todo")
    public Result<Todo> create(@RequestBody Todo todo) {
        todo = todoRepository.save(todo);
        return new Result(todo);
    }

    @DeleteMapping("/todo/{id}")
    public Result<Integer> delete(@PathVariable int id) {
        Result r = null;
        try {
            Todo todo = todoRepository.findById(id).orElseThrow();
            todoRepository.delete(todo);
            r = new Result(id);
        } catch (Exception  e) {
            r = new Result();
        }
        return r;
    }
}
