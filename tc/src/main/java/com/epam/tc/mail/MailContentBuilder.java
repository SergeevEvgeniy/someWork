package com.epam.tc.mail;

import com.epam.tc.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private static final String VIEW_NAME = "mail/reviewCourseMail";

    @Autowired
    private TemplateEngine templateEngine;

    public String build(Course course) {
        Context context = new Context();
        context.setVariable("course", course);
        return templateEngine.process(VIEW_NAME, context);
    }
}
