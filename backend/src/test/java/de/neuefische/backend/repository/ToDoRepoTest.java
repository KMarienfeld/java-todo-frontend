package de.neuefische.backend.repository;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.service.RandomId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ToDoRepoTest {

    @Test
    void getAllToDosAsList_getTwoTaks() {
        //given
        ToDo task1 = new ToDo("first Task", "OPEN", "111");
        ToDo task2 = new ToDo("second Task", "OPEN", "222");
        String id1 = "111";
        String id2 = "222";
        Map<String, ToDo> todoMap = new HashMap<>(Map.of(
                id1, task1,
                id2, task2
        ));
        ToDoRepo repo = new ToDoRepo(todoMap);

        List<ToDo> expected = new ArrayList<>(List.of(
                task1,
                task2
        ));
        //when
        List<ToDo> actual = repo.getAllToDosAsList();

        //then
        assertEquals(actual,expected);

    }

    @Test
    void addNewTask_addingOneNewTaskToTwoExistingTasks() {
        //given
        ToDo task1 = new ToDo("first Task", "OPEN", "111");
        ToDo task2 = new ToDo("second Task", "OPEN", "222");
        String id1 = "111";
        String id2 = "222";
        Map<String, ToDo> todoMap = new HashMap<>(Map.of(
                id1, task1,
                id2, task2
        ));
        ToDoRepo repo = new ToDoRepo(todoMap);
        ToDo newTask = new ToDo("new Task", "OPEN", "333");
        String newId = "333";
        //when
        ToDo actualNewTask = repo.addNewTask(newTask);
        Map<String, ToDo> expectedMap = new HashMap<>(Map.of(
                id1, task1,
                id2, task2,
                newId, newTask
        ));
        Map<String, ToDo> actualMap = repo.getToDoMap();

        //then
        assertEquals(actualNewTask, newTask);
        assertEquals(actualMap, expectedMap);

    }

    @Test
    void getActualTask_getTaskById() {
        //given
        ToDo task1 = new ToDo("first Task", "OPEN", "111");
        ToDo task2 = new ToDo("second Task", "OPEN", "222");
        String id1 = "111";
        String id2 = "222";
        Map<String, ToDo> todoMap = new HashMap<>(Map.of(
                id1, task1,
                id2, task2
        ));
        ToDoRepo repo = new ToDoRepo(todoMap);
        //when
        ToDo acutal = repo.getActualTask(id2);
        //then
        assertEquals(acutal, task2);
    }

    @Test
    void editTodo() {
        //given
        ToDo task1 = new ToDo("first Task", "OPEN", "111");
        ToDo task2 = new ToDo("second Task", "OPEN", "222");
        String id1 = "111";
        String id2 = "222";
        Map<String, ToDo> todoMap = new HashMap<>(Map.of(
                id1, task1,
                id2, task2
        ));
        ToDoRepo repo = new ToDoRepo(todoMap);
        ToDo newTask2 = new ToDo("second Task", "OPEN", "222");
        String id = "222";
        List<ToDo> expected = new ArrayList<>(List.of(
                task1,
                newTask2
        ));

        //when
        List<ToDo> actual = repo.editTodo(id, newTask2);

        //then
        assertEquals(actual, expected);
    }

    @Test
    void deleteTask() {
        //given
        ToDo task1 = new ToDo("first Task", "OPEN", "111");
        ToDo task2 = new ToDo("second Task", "OPEN", "222");
        String id1 = "111";
        String id2 = "222";
        Map<String, ToDo> todoMap = new HashMap<>(Map.of(
                id1, task1,
                id2, task2
        ));
        ToDoRepo repo = new ToDoRepo(todoMap);
        String deleteTaskId = "222";
        List<ToDo> expected = new ArrayList<>(List.of(
                task1
        ));
        //when
        List<ToDo> actual = repo.deleteTask(deleteTaskId);

        //then
        assertEquals(actual, expected);
    }


}