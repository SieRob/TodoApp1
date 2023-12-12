package be.vdab.todo_app1.controllers;

import be.vdab.todo_app1.domain.Mens;
import be.vdab.todo_app1.dto.NieuweMens;
import be.vdab.todo_app1.dto.NieuweTodo;
import be.vdab.todo_app1.domain.Todo;
import be.vdab.todo_app1.exceptions.MensNietGevondenException;
import be.vdab.todo_app1.services.MensService;
import be.vdab.todo_app1.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("mensen")
@ExposesResourceFor(Mens.class)
public class MensController {
    private final MensService mensService;
    private final TodoService todoService;
    private final TypedEntityLinks.ExtendedTypedEntityLinks<Mens> links;

    private record TodoBeknopt(String tekst, int prioriteit, LocalDateTime gemaakt){
        TodoBeknopt(Todo todo){
            this(todo.getTekst(), todo.getPrioriteit(), todo.getGemaakt());
        }
    }
    public MensController(MensService mensService, TodoService todoService, EntityLinks links) {
        this.mensService = mensService;
        this.todoService = todoService;
        this.links = links.forType(Mens.class, Mens::getId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    HttpHeaders createMens(@RequestBody @Valid NieuweMens nieuweMens){
        var mens = mensService.create(nieuweMens);
        var headers = new HttpHeaders();
        headers.setLocation(links.linkToItemResource(mens).toUri());
        return headers;
    }

    @PostMapping("{id}/todos")
    @ResponseStatus(HttpStatus.OK)
    void createTodo(@PathVariable long id, @RequestBody @Valid NieuweTodo nieuweTodo){

        var mens = mensService.findById(id).orElseThrow(MensNietGevondenException::new);
        System.out.println("Mens gevonden");
        todoService.createTodo(mens.getId(), nieuweTodo);
    }


    @GetMapping("{id}/todos")
    List<TodoBeknopt> findTodosByMensId(@PathVariable long id){
        var mens = mensService.findById(id).orElseThrow(MensNietGevondenException::new);
        return todoService.findTodosByMensId(mens.getId()).stream()
                .sorted(Comparator.comparing(Todo::getGemaakt))
                .map(todo -> new TodoBeknopt(todo))
                .toList();
    }

    @GetMapping("{id}")
    Mens findMensById(@PathVariable long id){
        return mensService.findById(id).orElseThrow(MensNietGevondenException::new);
    }
}
