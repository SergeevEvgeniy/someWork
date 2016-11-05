package com.epam.tc.web.controller;

import com.epam.tc.model.Course;
import com.epam.tc.security.AuthenticatedUser;
import com.epam.tc.service.course.CourseService;
import com.epam.tc.service.user.UserService;
import com.epam.tc.web.forms.CourseForm;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoursesController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private AuthenticatedUser authenticatedUser;
    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(CoursesController.class);
    private static final String ID = "id";

    @RequestMapping(value = {"/courses", "/*"}, method = RequestMethod.GET)
    public Model courses(Model model) {
        model.addAttribute("user", authenticatedUser.getUserEmail());
        model.addAttribute("courses", courseService.getAll());
        return model;
    }

    private boolean ifCourseExist(String id) {
        int id_int = Integer.parseInt(id);
        final Course course = courseService.getById(id_int);
        if (course == null) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = {"/courses/{id}"}, method = RequestMethod.GET)
    public ModelAndView details(@PathVariable(ID) String id) {
        ModelAndView mav;
        try {
            if (ifCourseExist(id)) {
                mav = new ModelAndView("courseDetails");
                mav.addObject("course", courseService.getById(Integer.parseInt(id)));
            } else {
                LOG.warn("Not found courses with id=", id);
                mav = new ModelAndView("troublePage");
            }
        } catch (NumberFormatException ex) {
            LOG.warn("Bad id=", id);
            mav = new ModelAndView("troublePage");
        }
        return mav.addObject("user", authenticatedUser.getUserEmail());
    }

    @RequestMapping(value = "/courses/create", method = RequestMethod.GET)
    public ModelAndView createCourse() {
        return new ModelAndView("create").addObject("user", authenticatedUser.getUserEmail());
    }

    @RequestMapping(value = "/courses/create", method = RequestMethod.POST)
    public void createCourse(final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException {

        Course course = new Course(
                req.getParameter("titleField"),
                req.getParameter("descriptionField"),
                req.getParameter("linksField"),
                userService.getUserByEmail(authenticatedUser.getUserEmail()));
        courseService.create(course);
        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = "/courses/{id}/update", method = RequestMethod.GET)
    public ModelAndView printForUpdateCourse(@PathVariable(ID) String id) {
        ModelAndView mav;

        if (ifCourseExist(id)) {
            mav = new ModelAndView("update");
            mav.addObject("course", courseService.getById(Integer.parseInt(id)));
        } else {
            mav = new ModelAndView("troublePage");
        }
        return mav.addObject("user", authenticatedUser.getUserEmail());
    }

    @RequestMapping(value = "/courses/{courseId}/update", method = RequestMethod.POST)
    public void updateCourse(final HttpServletResponse resp,
            @ModelAttribute CourseForm courseForm) throws IOException {

        if (ifCourseExist(courseForm.getCourseId())) {
            Course course = courseService.getById(Integer.parseInt(courseForm.getCourseId()));
            course.setName(courseForm.getName());
            course.setDescription(courseForm.getDescription());
            course.setLinks(courseForm.getLinks());
            courseService.update(course);

            resp.sendRedirect("/courses");
        } else {
            resp.sendRedirect("/troublePage");
        }
    }
}
