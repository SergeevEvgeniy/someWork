package com.epam.tc.dao.course;

import com.epam.tc.dao.CRUDdao;
import com.epam.tc.model.Course;

public interface CourseDao extends CRUDdao<Course> {

    public Course getByTopic(String name);
}
