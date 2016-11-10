package com.epam.tc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdParsingException extends RuntimeException {

    private static final Logger LOG = LoggerFactory.getLogger(IdParsingException.class);

    public IdParsingException(String message, NumberFormatException nfe) {
        LOG.warn(message);
    }
}
