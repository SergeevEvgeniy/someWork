package com.epam.tc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseNotFoundException extends RuntimeException {

    private static final Logger LOG = LoggerFactory.getLogger(CourseNotFoundException.class);

    public CourseNotFoundException(String message) {
        LOG.warn(message);
    }
}
