package com.epam.tc.service.course;

import com.epam.tc.model.Course;
import com.epam.tc.model.User;
import java.util.List;

public interface CourseService {

    void create(Course course);

    void delete(Course course);

    List<Course> getAll();

    List<Course> getUserCourses(User user);

    Course getById(int id);

    void update(Course course);

    void addSubscriber(int courseId, User subscriber);

    void addAttender(int courseId, User attender);

    List<Course> filterCourses(List<Course> courses, String filterCondition);
}
