package com.epam.tc.web.controller;

import com.epam.tc.model.Course;
import com.epam.tc.security.AuthenticatedUser;
import com.epam.tc.service.course.CourseService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createCourse(final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException {
        Course course = new Course(
                req.getParameter("titleField"),
                req.getParameter("descriptionField"),
                req.getParameter("linksField")
        );
        courseService.create(course);

        resp.sendRedirect("/courses");
    }
}
