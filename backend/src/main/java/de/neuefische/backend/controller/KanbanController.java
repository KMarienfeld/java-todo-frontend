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
    public ToDo addNewTask(@RequestBody String task) {
        return kanbanService.addNewTask(task);
    }

    @GetMapping("/board/todo")
    public List<ToDo> getAllTodosList() {
        return kanbanService.getListTodos();
    }
}
