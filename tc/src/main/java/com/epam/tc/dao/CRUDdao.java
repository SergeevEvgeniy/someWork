package com.epam.tc.dao;

import java.util.List;

public interface CRUDdao<EntityType> {

    public void create(EntityType object);

    public EntityType getById(int id);

    public List<EntityType> getAll();

    public void update(EntityType object);

    public void delete(EntityType object);
}
