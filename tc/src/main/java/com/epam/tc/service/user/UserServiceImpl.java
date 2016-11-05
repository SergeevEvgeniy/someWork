package com.epam.tc.service.user;

import com.epam.tc.dao.user.UserDao;
import com.epam.tc.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public User getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }
    
    @Override
    public User getUserByLogin(String login) {
        return userDao.getByLogin(login);
    }
    
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
    
    @Override
    public void create(User user) {
        userDao.create(user);
    }
}
