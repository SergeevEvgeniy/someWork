package com.epam.tc.service.userRole;

import com.epam.tc.model.UserRole;
import java.util.List;

public interface UserRoleService {

    List<UserRole> getAll();

    void create(UserRole userRole);

    UserRole getURbyName(String role);
}
