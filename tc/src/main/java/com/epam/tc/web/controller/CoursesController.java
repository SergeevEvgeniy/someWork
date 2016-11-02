package com.epam.tc.web.controller;

import com.epam.tc.security.AuthenticatedUser;
import com.epam.tc.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CoursesController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private AuthenticatedUser authenticatedUser;

    @RequestMapping(value = {"/courses", "/*"}, method = RequestMethod.GET)
    public Model courses(Model model) {
        model.addAttribute("user", authenticatedUser.getUserName());
        model.addAttribute("courses", courseService.getAll());
        return model;
    }
}
