package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class ToDo {

    private String description;
    private String status;
    private String id;

    public ToDo(String description, String status) {
    }
}
