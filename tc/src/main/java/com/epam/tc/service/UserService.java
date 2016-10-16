package com.epam.tc.service;

import com.epam.tc.model.User;

public interface UserService {

    User getUser(String login);
}
