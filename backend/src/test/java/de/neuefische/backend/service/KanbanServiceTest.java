package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repository.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KanbanServiceTest {

    ToDoRepo repo = mock (ToDoRepo.class);
    RandomId randomId = mock(RandomId.class);
    KanbanService service = new KanbanService(repo, randomId);

    @Test
    void getListTodos_returnTodoListWithOneTodo() {
        //given
        ToDo task = new ToDo("first task", "OPEN", "4");

        //when
        when(repo.getAllToDosAsList()).thenReturn(List.of(task));
        List<ToDo> actual = service.getListTodos();

        //then
        assertEquals(actual, List.of(task));
        verify(repo).getAllToDosAsList();
    }

    @Test
    void addNewTask_addOneNewTask() {
        //given
        ToDo task = new ToDo("first task", "OPEN");

        //when
        when(repo.addNewTask(task)).thenReturn(task);
        when(randomId.getRandomId()).thenReturn("111");
        task.setId("111");
        ToDo actual = service.addNewTask(task);

        //then
        assertEquals(actual, task);
        verify(randomId).getRandomId();
        verify(repo).addNewTask(task);

    }

    @Test
    void getTaskById() {
        //given
        String id = "111";
        ToDo task = new ToDo("first task", "OPEN", "111");

        //when
        when(repo.getActualTask(id)).thenReturn(task);
        ToDo actual = service.getActualTask(id);

        //then
        assertEquals(actual, task);
        verify(repo).getActualTask(id);
    }

    @Test
    void editTodo() {
        //given
        ToDo newTask = new ToDo("new task", "OPEN", "111");
        String id = "111";
        List<ToDo> newList = new ArrayList<>(List.of(
                newTask
        ));

        //when
        when(repo.editTodo(id, newTask)).thenReturn(newList);
        List<ToDo> actual = service.editTodo(id, newTask);

        //then
        assertEquals(actual, newList);
        verify(repo).editTodo(id, newTask);
    }

    @Test
    void deleteTaskById() {
        //given
        String id = "111";

        //when
        when(repo.deleteTask(id)).thenReturn(List.of());
        List<ToDo> acutal = service.deleteTask(id);

        //then
        assertEquals(acutal, List.of());
        verify(repo).deleteTask(id);
    }


}