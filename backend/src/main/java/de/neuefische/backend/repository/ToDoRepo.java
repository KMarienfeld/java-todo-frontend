package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Data
public class ToDoRepo {
    private final Map<String, ToDo> toDoMap = new HashMap<>();


    public List<ToDo> getAllToDosAsList(){
        return new ArrayList<>(toDoMap.values());
    }

    public ToDo addNewTask(ToDo task) {
        toDoMap.put(task.getId(), task);
        return task;
    }

    public ToDo getActualTask(String id) {
        return toDoMap.get(id);
    }

    public List<ToDo> editTodo(String id, ToDo task) {
        toDoMap.put(id, task);
        return getAllToDosAsList();
    }

    public List<ToDo> deleteTask(String id) {
        toDoMap.remove(id);
        return getAllToDosAsList();
    }
}
