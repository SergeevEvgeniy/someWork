package com.epam.tc.mail;

import com.epam.tc.model.Course;

public interface Mailler {

    void sendMail(Course course);
}
