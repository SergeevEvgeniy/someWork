package com.epam.tc.service.user;

import com.epam.tc.dao.user.UserDao;
import com.epam.tc.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Override
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
