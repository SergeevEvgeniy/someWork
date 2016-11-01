package com.epam.tc.service;

import com.epam.tc.model.Course;
import com.epam.tc.model.User;
import com.epam.tc.model.UserRole;
import com.epam.tc.service.course.CourseService;
import com.epam.tc.service.user.UserService;
import com.epam.tc.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultValuePopulator {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    public void CheckValue() {
        if (courseService.getAll().isEmpty()) {
            SetDefCourses();
        }
        if (userRoleService.getAll().isEmpty()) {
            setDefUserRoles();
        }
        if (userService.getAll().isEmpty()) {
            SetDefUsers();
        }
    }

    private void SetDefCourses() {
        Course course;

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

    private void setDefUserRoles() {
        UserRole userRole;

        userRole = new UserRole("Knowledge Manager");
        userRoleService.create(userRole);

        userRole = new UserRole("Department Manager");
        userRoleService.create(userRole);

        userRole = new UserRole("Lector");
        userRoleService.create(userRole);

        userRole = new UserRole("User");
        userRoleService.create(userRole);
    }

    private void SetDefUsers() {
        User user;

        user = new User("km@tc.edu", "km", "123", userRoleService.getURbyName("Knowledge Manager"));
        userService.create(user);

        user = new User("dm@tc.edu", "dm", "123", userRoleService.getURbyName("Department Manager"));
        userService.create(user);

        user = new User("lecture-a@tc.edu", "lecture-a", "123", userRoleService.getURbyName("Lector"));
        userService.create(user);

        user = new User("lecture-b@tc.edu", "lecture-b", "123", userRoleService.getURbyName("Lector"));
        userService.create(user);

        user = new User("user-a@tc.edu", "user-a", "123", userRoleService.getURbyName("User"));
        userService.create(user);

        user = new User("user-b@tc.edu", "user-b", "123", userRoleService.getURbyName("User"));
        userService.create(user);

        user = new User("user-c@tc.edu", "user-c", "123", userRoleService.getURbyName("User"));
        userService.create(user);
    }
}
