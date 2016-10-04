package com.epam.tc.dao;

import com.epam.tc.model.Course;
import java.util.List;

public interface CourseDao {

    public void create(Course course);

    public List<Course> getAll();

    public Course getById(int id);

    public Course getByName(String name);

    public void update(Course course);

    public void delete(Course course);
}
