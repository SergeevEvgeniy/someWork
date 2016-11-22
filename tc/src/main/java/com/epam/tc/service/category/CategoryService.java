package com.epam.tc.service.category;

import com.epam.tc.model.Category;
import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    void create(Category category);
    
    Category getByName(String name);
}
