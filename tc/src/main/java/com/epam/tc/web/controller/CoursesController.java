package com.epam.tc.web.controller;

import com.epam.tc.dto.UserDTO;
import com.epam.tc.exception.CourseNotFoundException;
import com.epam.tc.exception.IdParsingException;
import com.epam.tc.model.Category;
import com.epam.tc.model.Course;
import com.epam.tc.model.User;
import com.epam.tc.service.category.CategoryService;
import com.epam.tc.service.course.CourseService;
import com.epam.tc.service.evaluate.EvaluateService;
import com.epam.tc.service.status.StatusService;
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
    private UserService userService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StatusService statusService;

    private static final Logger LOG = LoggerFactory.getLogger(CoursesController.class);

    private static final String COURSEID = "courseId";
    private static final String PATH = "path";
    private Category filterCategory;

    @RequestMapping(value = {"/courses", "/*"}, method = RequestMethod.GET)
    public Model courses(final HttpServletRequest req, Model model) {

        filterCategory = req.getParameter("filterOption") != null
                ? categoryService.getByName(req.getParameter("filterOption"))
                : categoryService.getByName("All");

        model.addAttribute("courses", courseService.getAll(filterCategory));
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("person", new UserDTO(getCurrentUser()));
        model.addAttribute(PATH, "courses");
        return model;
    }

    @RequestMapping(value = {"/courses/{courseId}"}, method = RequestMethod.GET)
    public ModelAndView details(@PathVariable(COURSEID) String courseId) {
        ModelAndView mav;

        if (getCourse(courseId).getStatus().getName().equals("Draft")) {
            if (getCourse(courseId).getOwner().equals(getCurrentUser())) {
                mav = new ModelAndView("courseDetails");
                mav.addObject("course", getCourse(courseId));
                return mav;
            } else {
                return new ModelAndView("403");
            }
        } else {
            mav = new ModelAndView("courseDetails");
            mav.addObject("course", getCourse(courseId));
            return mav;
        }
    }

    @RequestMapping(value = "/courses/create", method = RequestMethod.GET)
    public ModelAndView createCourse() {
        return new ModelAndView("create")
                .addObject("categories", categoryService.getAll())
                .addObject("statuses", statusService.getAll());
    }

    @RequestMapping(value = "/courses/create", method = RequestMethod.POST)
    public void createCourse(final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException {

        Course course = new Course(
                req.getParameter("titleField"),
                req.getParameter("descriptionField"),
                req.getParameter("linksField"),
                getCurrentUser());
        course.setCategory(categoryService.getByName(req.getParameter("categoryOption")));
        course.setStatus(statusService.getByName(req.getParameter("statusOption")));
        courseService.create(course);
        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = "/courses/{courseId}/update", method = RequestMethod.GET)
    public ModelAndView printForUpdateCourse(@PathVariable(COURSEID) String courseId) {
        ModelAndView mav = new ModelAndView("troublePage");
        Course course = getCourse(courseId);
        String ownerEmail = course.getOwner().getEmail();
        if (ownerEmail.equals(userService.getUserEmail())) {
            mav = new ModelAndView("update");
            mav.addObject("course", course);
        }
        return mav.addObject("categories", categoryService.getAll());
    }

    @RequestMapping(value = "/courses/{courseId}/update", method = RequestMethod.POST)
    public void updateCourse(final HttpServletRequest req, final HttpServletResponse resp,
            @ModelAttribute CourseForm courseForm) throws IOException {

        Course course = getCourse(courseForm.getCourseId());
        course.setName(courseForm.getName());
        course.setDescription(courseForm.getDescription());
        course.setLinks(courseForm.getLinks());
        course.setCategory(categoryService.getByName(req.getParameter("categoryOption")));
        courseService.update(course);

        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = "/courses/{courseId}/subscribe", method = RequestMethod.GET)
    public ModelAndView printForSubscribeCourse(@PathVariable(COURSEID) String courseId) {
        if (!"".equals(userService.getUserEmail())) {
            ModelAndView mav = new ModelAndView("subscribe");
            mav.addObject("course", getCourse(courseId));
            return mav;
        } else {
            return new ModelAndView("403");
        }
    }

    @RequestMapping(value = "/courses/{courseId}/subscribe", method = RequestMethod.POST)
    public void subscribeOnCourse(final HttpServletResponse resp,
            @PathVariable(COURSEID) String courseId) throws IOException {
        courseService.addSubscriber(Integer.parseInt(courseId),
                getCurrentUser());
        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = "/courses/{courseId}/attend", method = RequestMethod.GET)
    public ModelAndView printAttendess(@PathVariable(COURSEID) String courseId) {
        ModelAndView mav;
        Course course = getCourse(courseId);
        String username = userService.getUserEmail();

        if (course.isSubscribed(userService.getUserByEmail(username))) {
            mav = new ModelAndView("attend");
            mav.addObject("course", course);
        } else {
            mav = new ModelAndView("403");
        }
        return mav;
    }

    @RequestMapping(value = "/courses/{courseId}/attend", method = RequestMethod.POST)
    public void attendOnCourse(final HttpServletResponse resp,
            @PathVariable(COURSEID) int courseId) throws IOException {
        courseService.addAttender(courseId,
                getCurrentUser());
        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = "/courses/{courseId}/evaluate", method = RequestMethod.GET)
    public ModelAndView evaluate(@PathVariable(COURSEID) String courseId) {
        ModelAndView mav;
        Course course = getCourse(courseId);
        User user = getCurrentUser();

        if ((course.isAttended(user)) && (!course.hasGrade(user))) {
            mav = new ModelAndView("evaluate");
            mav.addObject("course", course);
        } else {
            mav = new ModelAndView("403");
        }
        return mav;
    }

    @RequestMapping(value = "/courses/{courseId}/evaluate", method = RequestMethod.POST)
    public void evaluateCourse(final HttpServletRequest req, final HttpServletResponse resp,
            @PathVariable(COURSEID) String courseId) throws IOException {
        try {
            int grade = Integer.parseInt(req.getParameter("grade"));
            Course course = getCourse(courseId);
            if ((grade >= 1) && (grade <= 5)) {
                User user = getCurrentUser();
                evaluateService.setGrade(course, user, grade);
            } else {
                LOG.warn("Incorect grade value: ");
            }
        } catch (NumberFormatException nfe) {
            throw new IdParsingException("Cannot parse to int grade: " + req.getParameter("grade"), nfe);
        }
        resp.sendRedirect("/courses");
    }

    @RequestMapping(value = {"/courses/{courseId}/participants"}, method = RequestMethod.GET)
    public ModelAndView participants(@PathVariable(COURSEID) String courseId) {
        ModelAndView mav = new ModelAndView("participants");
        mav.addObject("course", getCourse(courseId));
        return mav;
    }

    @RequestMapping(value = {"/mycourses"}, method = RequestMethod.GET)
    public ModelAndView myCourses(final HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("myCourses");
        User user = getCurrentUser();
        UserDTO userDTO = new UserDTO(user);

        filterCategory = req.getParameter("filterOption") != null
                ? categoryService.getByName(req.getParameter("filterOption"))
                : categoryService.getByName("All");

        mav.addObject("person", userDTO);
        mav.addObject("courses", courseService.getUserCourses(getCurrentUser(), filterCategory));
        mav.addObject("categories", categoryService.getAll());
        mav.addObject(PATH, "mycourses");
        return mav;
    }

    @ModelAttribute("userEmail")
    public String getUserEmail() {
        return userService.getUserEmail();
    }

    private User getCurrentUser() {
        return userService.getUserByEmail(userService.getUserEmail());
    }

    private Course getCourse(String courseId) {
        try {
            int id = Integer.parseInt(courseId);
            Course course = courseService.getById(id);
            if (course == null) {
                throw new CourseNotFoundException("Course with id: " + courseId + " not found");
            }
            return course;
        } catch (NumberFormatException nfe) {
            throw new IdParsingException("Cannot parse to int courseId: " + courseId, nfe);
        }
    }

    @RequestMapping(value = "/courses/{courseId}/send_to_review", method = RequestMethod.POST)
    public void sendToReview(final HttpServletResponse resp,
            @ModelAttribute CourseForm courseForm) throws IOException {

        Course course = getCourse(courseForm.getCourseId());
        course.setStatus(statusService.getByName("Proposal"));
        courseService.update(course);
        resp.sendRedirect("/courses");
    }
}
