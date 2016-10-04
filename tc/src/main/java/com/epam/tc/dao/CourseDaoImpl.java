package com.epam.tc.dao;

import com.epam.tc.model.Course;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Course newCourse) {
        entityManager.persist(newCourse);
    }

    @Override
    public void delete(Course course) {
        if (entityManager.contains(course)) {
            entityManager.remove(course);
        } else {
            entityManager.remove(entityManager.merge(course));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> getAll() {
        return entityManager.createQuery("select c from Course c").getResultList();
    }

    @Override
    public Course getById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public Course getByName(String courseName) {
        return null;
    }

    @Override
    public void update(Course changedCourse) {
        entityManager.merge(changedCourse);
    }
}
