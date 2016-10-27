package com.epam.tc.dao.course;

import com.epam.tc.dao.CRUDdao;

public interface CourseDao<Course> extends CRUDdao<Course> {

    public Course getByTopic(String name);
}
