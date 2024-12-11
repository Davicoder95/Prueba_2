package com.irwi.assessment.domain.ports.service.crud;

public interface AssignUsersService<Entity,IdEntity, IdUser>{
    public Entity assignUser(IdEntity idEntity, IdUser idUsers);
}
