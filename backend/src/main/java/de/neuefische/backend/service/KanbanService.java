package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.ToDoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class KanbanService {
    private final ToDoRepo toDoRepo;


    public List<ToDo> getListTodos() {
        return toDoRepo.getAllToDosAsList();
    }

    public ToDo addNewTask(String task) {
        return toDoRepo.addNewTask(task);
    }
}
