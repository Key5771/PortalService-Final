package kr.ac.jejunu.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor // final에 대한 객체에 생명력을 넣어줌.
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping("/todo")
    public List<Todo> todo() {
        return todoRepository.findAll();
    }
}
