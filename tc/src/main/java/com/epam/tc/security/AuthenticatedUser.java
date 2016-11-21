package com.epam.tc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUser {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticatedUser.class);

    public String getUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.equals("anonymousUser")) {
            UserDetails ud = (UserDetails) principal;
            return ud.getUsername();
        } else {
            LOG.info("anonymousUser in system");
            return "anonymousUser";
        }
    }
}
