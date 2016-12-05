package com.epam.tc.web.controller;

import com.epam.tc.init.SampleDataPopulator;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(){}

    @Autowired
    SampleDataPopulator defaultValuePopulator;

    @PostConstruct
    public void init() {
        defaultValuePopulator.initDBvaluesIsEmpty();
    }
}
