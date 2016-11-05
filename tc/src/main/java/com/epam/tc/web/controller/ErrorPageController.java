package com.epam.tc.web.controller;

import com.epam.tc.security.AuthenticatedUser;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorPageController implements ErrorController {

    @Autowired
    private AuthenticatedUser authenticatedUser;

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/courses");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public Model accessDenied(Model model) {
        return model.addAttribute("user", authenticatedUser.getUserName());
    }
}
