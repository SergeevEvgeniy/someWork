package com.epam.tc.web.controller;

import com.epam.tc.security.AuthenticatedUser;
import com.epam.tc.service.course.CourseService;
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
    
    @RequestMapping(value = {"/courses/{id}"}, method = RequestMethod.GET)
    public ModelAndView details(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("courseDetails");
        mav.addObject("course", courseService.getById(id));
        mav.addObject("user", authenticatedUser.getUserName());
        return mav;
    }
}
