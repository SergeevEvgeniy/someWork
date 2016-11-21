package com.epam.tc.dao.user;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends CRUDdaoImpl<User> implements UserDao {

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User getByLogin(String login) {
        try {
            return entityManager.createQuery("select u from User u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.info("User with login {0} not found", login);
            LOG.debug("No user found ", e);
            return null;
        }
    }

    @Override
    public User getByEmail(String email) {
        try {
            return entityManager.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.info("User with email {0} not found", email);
            LOG.debug("No user found ", e);
            return null;
        }
    }
}
