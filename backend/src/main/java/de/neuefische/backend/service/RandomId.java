package de.neuefische.backend.service;


import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class RandomId {


    public String getRandomId() {
        return UUID.randomUUID().toString();
    }
}
