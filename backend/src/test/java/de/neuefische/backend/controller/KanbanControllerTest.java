package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class KanbanControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    @DirtiesContext
    void getAllTodos_returnInitialEmptyListOfTodos_return200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void addNewTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType("application/json")
                .content("""
                { 
                    "description": "Hallo", 
                    "status":"OPEN"
                }
    """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                { 
                    "description": "Hallo", 
                    "status":"OPEN"
                }

"""))
                .andExpect(jsonPath("$.id").isNotEmpty());

    }

    @Test
    @DirtiesContext
    void getAllTodosList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void getTodoByID_return200Ok_andCorrectTodo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType("application/json")
                        .content("""
                        { 
                            "description": "Hallo", 
                            "status":"OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo todo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/"+todo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                { 
                    "description": "Hallo", 
                    "status":"OPEN"
                }
                """))
                .andExpect(jsonPath("$.id").value(todo.getId()));

    }
    @Test
    @DirtiesContext
    void getTodoById_returnStatus404_throwResponseStatusExceptions() throws Exception {
        //given
        //when + then
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/imInvalid"))
            .andExpect(status().is(404));
        } catch (ResponseStatusException e) {
            assertEquals("No User find with ID: imInvalid", e.getCause().getMessage());
        }

    }
    @Test
    @DirtiesContext
    void editTodo_ThenReturn200OK_ReturnCorrectTask() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType("application/json")
                        .content("""
                        { 
                            "description": "Hallo", 
                            "status":"OPEN"
                        }
                        """))
                        .andExpect(status().isOk())
                        .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo todo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/"+todo.getId())
                        .contentType("application/json")
                        .content("""
                        { 
                            "description": "bye", 
                            "status":"OPEN"
                        }
                        """))
                        .andExpect(status().isOk())
                        .andExpect(content().json("""
                        [{ 
                            "description": "bye", 
                            "status":"OPEN"
                        }]
                                
                                """));
    }

    @Test
    void deleteTask() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType("application/json")
                        .content("""
                        { 
                            "description": "Hallo", 
                            "status":"OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ToDo todo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/"+todo.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().json("""
                        []
                                
                                """));

    }


}