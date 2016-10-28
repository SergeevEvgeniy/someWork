package com.epam.tc.dao.course;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.Course;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl extends CRUDdaoImpl<Course> implements CourseDao {

    private static final Logger LOG = LoggerFactory.getLogger(CourseDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public CourseDaoImpl() {
        super(Course.class);
    }

    public Course getByTopic(String name) {
        try {
            return entityManager.createQuery("select c from Course c where c.name = :name", Course.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.info("Course with topic {0} not found", name);
            LOG.debug("No course found ", e);
            return null;
        }
    }
}
