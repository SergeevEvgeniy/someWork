package com.epam.tc.web.controller;

import com.epam.tc.model.Course;
import com.epam.tc.security.AuthenticatedUser;
import com.epam.tc.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseDetailController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private AuthenticatedUser authenticatedUser;
    private static final Logger LOG = LoggerFactory.getLogger(CourseDetailController.class);

    @RequestMapping(value = {"/courses/{id}"}, method = RequestMethod.GET)
    public ModelAndView details(@PathVariable("id") String id) {
        ModelAndView mav;
        try {
            int id_int = Integer.parseInt(id);
            final Course course = courseService.getById(id_int);
            if (course != null) {
                mav = new ModelAndView("courseDetails");
                mav.addObject("course", course);
                mav.addObject("user", authenticatedUser.getUserName());
            } else {
                LOG.warn("Not found courses with id=", id);
                mav = new ModelAndView("troublePage");
            }
        } catch (NumberFormatException ex) {
            LOG.warn("Bad id=", id);
            mav = new ModelAndView("troublePage");
        }

        return mav;
    }
}
