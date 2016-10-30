package com.epam.tc.service.user;

import com.epam.tc.model.User;
import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserByEmail(String login);
    
    void create(User user);
}
