package com.epam.tc.web.controller;

import com.epam.tc.exception.CourseNotFoundException;
import com.epam.tc.exception.IdParsingException;
import com.epam.tc.model.Course;
import com.epam.tc.model.User;
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

    private static final String ID = "id";

    @RequestMapping(value = {"/courses", "/*"}, method = RequestMethod.GET)
    public Model courses(Model model) {
        model.addAttribute("user", authenticatedUser.getUserEmail());
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("userEmail", userService.getUserByEmail(authenticatedUser.getUserEmail()));
        return model;
    }

    private Course getCourse(String id) {
        try {
            int courseId = Integer.parseInt(id);
            Course course = courseService.getById(courseId);
            if (course == null) {
                throw new CourseNotFoundException("Course with id: " + id + "not found");
            }
            return course;
        } catch (NumberFormatException nfe) {
            throw new IdParsingException("Cannot parse to int courseId: " + id, nfe);
        }
    }

    @RequestMapping(value = {"/courses/{id}"}, method = RequestMethod.GET)
    public ModelAndView details(@PathVariable(ID) String id) {
        ModelAndView mav;
        mav = new ModelAndView("courseDetails");
        mav.addObject("course", getCourse(id));

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
        ModelAndView mav = new ModelAndView("troublePage");
        Course course = getCourse(id);
        String ownerEmail = course.getOwner().getEmail();
        if (ownerEmail.equals(authenticatedUser.getUserEmail())) {
            mav = new ModelAndView("update");
            mav.addObject("course", course);
        }
        return mav.addObject("user", authenticatedUser.getUserEmail());
    }

    @RequestMapping(value = "/courses/{courseId}/update", method = RequestMethod.POST)
    public void updateCourse(final HttpServletResponse resp,
            @ModelAttribute CourseForm courseForm) throws IOException {

        Course course = getCourse(courseForm.getCourseId());
        course.setName(courseForm.getName());
        course.setDescription(courseForm.getDescription());
        course.setLinks(courseForm.getLinks());
        courseService.update(course);

        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = "/courses/{id}/subscribe", method = RequestMethod.GET)
    public ModelAndView printForSubscribeCourse(@PathVariable(ID) String id) {
        if (!"".equals(authenticatedUser.getUserEmail())) {
            ModelAndView mav = new ModelAndView("subscribe");
            mav.addObject("course", getCourse(id));
            return mav.addObject("user", authenticatedUser.getUserEmail());
        } else {
            return new ModelAndView("403");
        }
    }

    @RequestMapping(value = "/courses/{id}/subscribe", method = RequestMethod.POST)
    public void SubscribeOnCourse(final HttpServletResponse resp,
            @PathVariable(ID) String id) throws IOException {
        courseService.addSubscriber(Integer.parseInt(id),
                userService.getUserByEmail(authenticatedUser.getUserEmail()));
        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = "/courses/{id}/attend", method = RequestMethod.GET)
    public ModelAndView printAttendess(@PathVariable(ID) String id) {
        ModelAndView mav;
        Course course = getCourse(id);
        String username = authenticatedUser.getUserEmail();

        if (course.isSubscribed(userService.getUserByEmail(username))) {
            mav = new ModelAndView("attend");
            mav.addObject("course", course);
        } else {
            mav = new ModelAndView("403");
        }
        return mav.addObject("user", authenticatedUser.getUserEmail());
    }

    @RequestMapping(value = "/courses/{id}/attend", method = RequestMethod.POST)
    public void AttendOnCourse(final HttpServletResponse resp,
            @PathVariable(ID) int courseId) throws IOException {
        courseService.addAttender(courseId,
                userService.getUserByEmail(authenticatedUser.getUserEmail()));
        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = "/courses/{id}/evaluate", method = RequestMethod.GET)
    public ModelAndView evaluate(@PathVariable(ID) String id) {
        ModelAndView mav;
        Course course = getCourse(id);
        String username = authenticatedUser.getUserEmail();

        if (course.isAttended(userService.getUserByEmail(username))) {
            mav = new ModelAndView("evaluate");
            mav.addObject("course", course);
        } else {
            mav = new ModelAndView("403");
        }
        return mav.addObject("user", authenticatedUser.getUserEmail());
    }

    private static final Logger LOG = LoggerFactory.getLogger(CoursesController.class);

    @RequestMapping(value = "/courses/{id}/evaluate", method = RequestMethod.POST)
    public void EvaluateCourse(final HttpServletRequest req,
            final HttpServletResponse resp, @PathVariable(ID) int courseId) throws IOException {
        try {
            int grade = Integer.parseInt(req.getParameter("grade"));

            if ((grade >= 1) && (grade <= 5)) {
                User user = userService.getUserByEmail(authenticatedUser.getUserEmail());
                courseService.setGrade(courseId, user, grade);
            } else {
                LOG.warn("Incorect grade value: ");
            }
        } catch (NumberFormatException nfe) {
            throw new IdParsingException("Cannot parse to int grade: " + req.getParameter("grade"), nfe);
        }

        resp.sendRedirect("/courses");
    }
}
