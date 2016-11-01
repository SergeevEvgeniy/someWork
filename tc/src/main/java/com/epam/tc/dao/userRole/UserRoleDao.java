package com.epam.tc.dao.userRole;

import com.epam.tc.dao.CRUDdao;
import com.epam.tc.model.UserRole;

public interface UserRoleDao extends CRUDdao<UserRole> {

    UserRole getByName(String role);
}
