package com.epam.tc.web.controller;

import com.epam.tc.service.DefaultValuePopulator;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Model login(Model model) {
        return model;
    }

    @RequestMapping(value = "/LoginController", method = RequestMethod.POST)
    public void logout(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/courses");
    }

    @Autowired
    DefaultValuePopulator dvChecker;

    @PostConstruct
    public void init() {
        dvChecker.CheckValue();
    }
}
