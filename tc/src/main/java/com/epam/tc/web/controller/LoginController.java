package com.epam.tc.web.controller;

import com.epam.tc.service.DefaultValuePopulator;
import javax.annotation.PostConstruct;
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

    @Autowired
    DefaultValuePopulator dvChecker;

    @PostConstruct
    public void init() {
        dvChecker.CheckValue();
    }
}
