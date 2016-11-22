package com.epam.tc.dao.category;

import com.epam.tc.dao.CRUDdao;
import com.epam.tc.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends CRUDdao<Category> {

    Category getByName(String name);
}
