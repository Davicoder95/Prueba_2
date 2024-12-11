package com.irwi.assessment.controller.crud;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReadAllController <Entity>{
    public ResponseEntity<List<Entity>> readAll();
}
