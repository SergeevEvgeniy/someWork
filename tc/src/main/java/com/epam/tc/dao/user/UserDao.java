package com.epam.tc.dao.user;

import com.epam.tc.dao.CRUDdao;
import com.epam.tc.model.User;

public interface UserDao extends CRUDdao<User> {

    public User getByLogin(String login);

    public User getByEmail(String email);

}
