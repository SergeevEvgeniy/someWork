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
        try {
            UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ud.getUsername();
        } catch (ClassCastException ex) {
            LOG.warn("Not authenticated  user");
            return "surfer";
        }
    }
}
