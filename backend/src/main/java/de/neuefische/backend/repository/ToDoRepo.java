package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Data
public class ToDoRepo {
    private final List<ToDo> toDoList = new ArrayList<>();


    public List<ToDo> getAllToDosAsList(){
        return toDoList;
    }


    public ToDo addNewTask(String task) {
        ToDo toDo = new ToDo(task);
        toDoList.add(toDo);
        return toDo;
    }
}
