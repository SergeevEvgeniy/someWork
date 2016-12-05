package com.epam.tc.dao.course;

import com.epam.tc.dao.CRUDdao;
import com.epam.tc.model.Category;
import com.epam.tc.model.Course;
import com.epam.tc.model.User;
import java.util.List;

public interface CourseDao extends CRUDdao<Course> {

    public Course getByName(String name);

    public List<Course> getUserCourses(User user, Category category);

    public List<Course> getByCategory(Category category);
}
