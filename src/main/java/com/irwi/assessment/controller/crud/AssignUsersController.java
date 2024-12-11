package com.irwi.assessment.controller.crud;

import org.springframework.http.ResponseEntity;

public interface AssignUsersController<Entity ,IdEntity, IdUser>{
    public ResponseEntity<Entity> assignUser(IdEntity idEntity, IdUser idUsers);
}
