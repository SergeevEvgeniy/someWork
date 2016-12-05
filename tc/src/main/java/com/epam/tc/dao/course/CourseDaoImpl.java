package com.epam.tc.dao.course;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.Category;
import com.epam.tc.model.Course;
import com.epam.tc.model.User;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Course getByName(String name) {
        try {
            return entityManager.createQuery("select c from Course c where c.name = :name", Course.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.info("Course with name {0} not found", name);
            LOG.debug("No course found ", e);
            return null;
        }
    }

    @Override
    public List<Course> getUserCourses(User user, Category category) {
        List<Course> courses = new ArrayList<>();

        String query = "select c from Course c join c.subscribers s where s.id = :id";
        if (!category.getName().equals("All")) {
            query += " and c.category = :category";
            courses.addAll(entityManager.createQuery(query, Course.class)
                    .setParameter("id", user.getId())
                    .setParameter("category", category)
                    .getResultList());
        } else {
            courses.addAll(entityManager.createQuery(query, Course.class)
                    .setParameter("id", user.getId())
                    .getResultList());
        }

        query = "select c from Course c join c.attenders a where a.user= :user";
        if (!category.getName().equals("All")) {
            query += " and c.category = :category";
            courses.addAll(entityManager.createQuery(query, Course.class)
                    .setParameter("user", user)
                    .setParameter("category", category)
                    .getResultList());
        } else {
            courses.addAll(entityManager.createQuery(query, Course.class)
                    .setParameter("user", user)
                    .getResultList());
        }

        query = "select c from Course c where c.owner = :user";
        if (!category.getName().equals("All")) {
            query += " and c.category = :category";
            courses.addAll(entityManager.createQuery(query, Course.class)
                    .setParameter("user", user)
                    .setParameter("category", category)
                    .getResultList());
        } else {
            courses.addAll(entityManager.createQuery(query, Course.class)
                    .setParameter("user", user)
                    .getResultList());
        }

        return courses;
    }

    @Override
    public List<Course> getByCategory(Category category) {
        return entityManager
                .createQuery("select c from Course c where c.category = :category", Course.class)
                .setParameter("category", category).getResultList();
    }
}
