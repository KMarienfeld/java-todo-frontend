package de.neuefische.backend.controller;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.service.KanbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor

public class KanbanController {

    private final KanbanService kanbanService;

    @GetMapping ("api/todo")
    public List<ToDo> getAllTodos() {
        return kanbanService.getListTodos();
    }

    @PostMapping("api/todo")
    public ToDo addNewTask(@RequestBody ToDo task) {
        return kanbanService.addNewTask(task);
    }

    @GetMapping("/board/todo")
    public List<ToDo> getAllTodosList() {
        return kanbanService.getListTodos();
    }

    @GetMapping("/api/todo/{id}")
    public ToDo getDetails(@PathVariable String id) {
        return kanbanService.getActualTask(id);
    }

    @PutMapping("/api/todo/{id}")
    public List<ToDo> editTodo(@PathVariable String id, @RequestBody ToDo task) {
        return kanbanService.editTodo(id, task);
    }

    @DeleteMapping("/api/todo/{id}")
    public List<ToDo> deleteTask(@PathVariable String id) {
        return kanbanService.deleteTask(id);
    }

}
