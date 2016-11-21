package com.epam.tc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdBorderException extends RuntimeException {

    private static final Logger LOG = LoggerFactory.getLogger(IdParsingException.class);

    public IdBorderException(String message) {
        LOG.warn(message);
    }
}
