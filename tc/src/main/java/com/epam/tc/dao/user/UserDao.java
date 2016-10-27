package com.epam.tc.dao.user;

import com.epam.tc.dao.CRUDdao;

public interface UserDao<User> extends CRUDdao<User> {

    public User getByLogin(String login);

    public User getByEmail(String email);

}
