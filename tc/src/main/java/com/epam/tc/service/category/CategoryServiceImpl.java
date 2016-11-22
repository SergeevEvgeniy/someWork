package com.epam.tc.service.category;

import com.epam.tc.dao.category.CategoryDao;
import com.epam.tc.model.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public void create(Category category) {
        categoryDao.create(category);
    }

    @Override
    public Category getByName(String name) {
        return categoryDao.getByName(name);
    }
}
