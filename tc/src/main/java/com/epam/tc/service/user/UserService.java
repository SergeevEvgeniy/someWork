package com.epam.tc.service.user;

import com.epam.tc.model.User;

public interface UserService {

    User getUserByEmail(String login);
}
