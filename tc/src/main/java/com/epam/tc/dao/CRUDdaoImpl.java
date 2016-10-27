package com.epam.tc.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class CRUDdaoImpl<EntityType> implements CRUDdao<EntityType> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class clazz;

    public CRUDdaoImpl(Class clazz) {
        this.clazz = clazz;
    }

    public void create(EntityType currentEntity) {
        entityManager.persist(currentEntity);
    }

    public EntityType getById(int id) {
        return (EntityType) entityManager.find(clazz, id);
    }

    public List<EntityType> getAll() {
        return entityManager.createQuery("select o from " + clazz.getSimpleName() + " o").getResultList();
    }

    public void update(EntityType cerrentEntity) {
        entityManager.merge(cerrentEntity);
    }

    public void delete(EntityType currentEntity) {
        entityManager.remove(currentEntity);
    }
}
