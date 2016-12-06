package com.epam.tc.dao.category;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.Category;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends CRUDdaoImpl<Category> implements CategoryDao {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public Category getByName(String name) {
        try {
            return entityManager.createQuery("select c from Category c where c.name = :name", Category.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.info("Category {0} not found", name);
            LOG.debug("No userRole found ", e);
            return null;
        }
    }
}
