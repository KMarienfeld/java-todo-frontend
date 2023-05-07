package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.ToDoRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class KanbanService {
    private final ToDoRepo toDoRepo;
    private final RandomId randomId;


    public List<ToDo> getListTodos() {
        return toDoRepo.getAllToDosAsList();
    }

    public ToDo addNewTask(ToDo task) {
        task.setId(randomId.getRandomId());
        return toDoRepo.addNewTask(task);
    }

    public ToDo getActualTask(String id) {
        return toDoRepo.getActualTask(id);
    }

    public List<ToDo> editTodo(String id , ToDo task) {
        return toDoRepo.editTodo(id, task);
    }

    public List<ToDo> deleteTask(String id) {
        return toDoRepo.deleteTask(id);
    }
}
