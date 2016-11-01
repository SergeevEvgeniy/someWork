package com.epam.tc.web.controller;

import com.epam.tc.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CoursesController {

    @Autowired
    private CourseService courseService;
    
    @RequestMapping(value = {"/courses", "/*"}, method = RequestMethod.GET)
    public Model courses(Model model) {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", ud.getUsername());
        model.addAttribute("courses", courseService.getAll());
        return model;
    }
}
