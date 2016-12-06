package com.epam.tc.dao.evaluate;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.Course;
import com.epam.tc.model.Evaluate;
import com.epam.tc.model.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EvaluateDaoImpl extends CRUDdaoImpl<Evaluate> implements EvaluateDao {

    private static final Logger LOG = LoggerFactory.getLogger(EvaluateDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public EvaluateDaoImpl() {
        super(Evaluate.class);
    }

    @Override
    public Evaluate getByCourseAndUser(Course course, User user) {
        try {
            return entityManager.createQuery("select c from Evaluate c where c.course = :course and c.user = :user", Evaluate.class)
                    .setParameter("course", course)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.info("Category with course {0} and user {1} not found", course.getName(), user.getEmail());
            LOG.debug("No course found ", e);
            return null;
        }
    }

}
