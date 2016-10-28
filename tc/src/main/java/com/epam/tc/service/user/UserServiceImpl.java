package com.epam.tc.service.user;

import com.epam.tc.dao.user.UserDao;
import com.epam.tc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByEmail(String login) {
        return userDao.getByEmail(login);
    }

}
