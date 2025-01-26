package net.javaguides.todo.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    // Build Add REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    // Build Get Todo REST API
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){

        TodoDto getTodo = todoService.getTodo(todoId);

        return ResponseEntity.ok(getTodo);
    }

    // Build Get All Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){

        List<TodoDto> todos = todoService.getAllTodos();

        return ResponseEntity.ok(todos);
    }

    // Build Update Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<String> updateTodo(@PathVariable("id") Long todoId,
                                             @RequestBody TodoDto todoDto){

        todoService.updateTodo(todoId,todoDto);

        return ResponseEntity.ok("Todo updated successfully!");
    }

    // Build Delete Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){

        todoService.deleteTodo(todoId);

        return ResponseEntity.ok("Todo Successfully deleted");
    }


    // Build Completed Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto>  completeTodo(@PathVariable("id") Long todoId){

        TodoDto completedTodo = todoService.completeTodo(todoId);

        return ResponseEntity.ok(completedTodo);
    }

    // Build In completed Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId){

        TodoDto inCompletedTodo = todoService.inCompleteTodo(todoId);

        return ResponseEntity.ok(inCompletedTodo);
    }
}
