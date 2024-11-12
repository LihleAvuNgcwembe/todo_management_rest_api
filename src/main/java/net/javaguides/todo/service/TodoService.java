package net.javaguides.todo.service;

import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(Long todoId, TodoDto todoDto);

    void deleteTodo(Long todoId);

    TodoDto completeTodo(Long todoId);

    TodoDto inCompleteTodo(Long todoId);
}
