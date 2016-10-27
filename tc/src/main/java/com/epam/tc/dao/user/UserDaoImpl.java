package com.epam.tc.dao.user;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends CRUDdaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
        super(User.class);
    }

    public User getByLogin(String login) {
        try {
            return entityManager.createQuery("select u from Users u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getByEmail(String email) {
        try {
            return entityManager.createQuery("select u from Users u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
