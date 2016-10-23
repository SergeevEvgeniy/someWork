package com.epam.tc.service;

import com.epam.tc.model.Course;
import com.epam.tc.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Женя
 */
public class DefValueChecker {

  //  @Autowired
    private CourseService courseService;

    private Course course;

    public DefValueChecker() {
//        if (courseService.getAll().isEmpty()) {
//            SetDefCourses();
//        }
    }

    private void SetDefCourses() {
        course = new Course("Draft");
        courseService.create(course);
        course = new Course("Proposal");
        courseService.create(course);
        course = new Course("Rejected");
        courseService.create(course);
        course = new Course("New");
        courseService.create(course);
        course = new Course("Open");
        courseService.create(course);
        course = new Course("Ready");
        courseService.create(course);
        course = new Course("In Progress");
        courseService.create(course);
        course = new Course("Finished");
        courseService.create(course);
    }
}
