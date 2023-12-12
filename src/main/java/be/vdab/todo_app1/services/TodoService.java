package be.vdab.todo_app1.services;

import be.vdab.todo_app1.dto.NieuweTodo;
import be.vdab.todo_app1.domain.Todo;
import be.vdab.todo_app1.repositories.TodoRepository2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository2 todoRepository;

    public TodoService(TodoRepository2 todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public void createTodo(long id, NieuweTodo nieuweTodo) {
        var todo = new Todo(id, nieuweTodo.tekst(), nieuweTodo.prioriteit());
        System.out.println(todo);
        todoRepository.createTodo(todo);
    }

    public List<Todo> findTodosByMensId(long id) {
        return todoRepository.findById(id);
    }
}
