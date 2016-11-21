package com.epam.tc.service.evaluate;

import com.epam.tc.model.Course;
import com.epam.tc.model.User;

public interface EvaluateService {

    void setGrade(Course course, User attender, int grade);
}
