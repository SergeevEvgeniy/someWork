package com.epam.tc.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "userRoles")
public class UserRole extends AbstractEntity implements Serializable{
    private String userRole;

    public UserRole() {
    }

    public UserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getRole() {
        return userRole;
    }

    public void setRole(String userRole) {
        this.userRole = userRole;
    }    
}
