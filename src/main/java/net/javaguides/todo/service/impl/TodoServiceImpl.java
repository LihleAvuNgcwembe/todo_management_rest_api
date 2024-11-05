package net.javaguides.todo.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.repository.TodoRepository;
import net.javaguides.todo.service.TodoService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // Convert TodoDto into Todo Jpa Entity
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        // Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);

        // Convert saved Todo Jpa entity object into TodoDto object
        TodoDto savedToTodoDto = new TodoDto();
        savedToTodoDto.setId(savedTodo.getId());
        savedToTodoDto.setTitle(savedTodo.getTitle());
        savedToTodoDto.setDescription(savedTodo.getDescription());
        savedToTodoDto.setCompleted(savedTodo.isCompleted());

        return savedToTodoDto;
    }
}
