package com.irwi.assessment.domain.ports.service.crud;

public interface ReadByIdService<Entity, ID> {
    public Entity readById(ID id);
}
