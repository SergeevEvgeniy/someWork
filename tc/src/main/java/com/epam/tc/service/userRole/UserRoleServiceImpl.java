package com.epam.tc.service.userRole;

import com.epam.tc.dao.userRole.UserRoleDao;
import com.epam.tc.model.UserRole;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> getAll() {
        return userRoleDao.getAll();
    }

    @Override
    public void create(UserRole userRole) {
        userRoleDao.create(userRole);
    }

    @Override
    public UserRole getURbyName(String role) {
        return userRoleDao.getByName(role);
    }
}
