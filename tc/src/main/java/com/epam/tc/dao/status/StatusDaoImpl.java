package com.epam.tc.dao.status;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.Status;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StatusDaoImpl extends CRUDdaoImpl<Status> implements StatusDao {

    private static final Logger LOG = LoggerFactory.getLogger(StatusDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public StatusDaoImpl() {
        super(Status.class);
    }

    @Override
    public Status getByName(String name) {
        try {
            return entityManager.createQuery("select s from Status s where s.name = :name", Status.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.info("Category {0} not found", name);
            LOG.debug("No userRole found ", e);
            return null;
        }
    }

}
