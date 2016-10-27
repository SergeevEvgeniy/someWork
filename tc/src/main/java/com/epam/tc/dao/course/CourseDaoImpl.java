package com.epam.tc.dao.course;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.Course;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl extends CRUDdaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CourseDaoImpl() {
        super(Course.class);
    }


    public Course getByTopic(String name) {
        try {
            return entityManager.createQuery("select c from Courses c where c.name = :name", Course.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
