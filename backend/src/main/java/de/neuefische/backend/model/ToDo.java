package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ToDo {

    private String description;
    private String status;
    private String id;
}
