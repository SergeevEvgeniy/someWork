package com.epam.tc.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
