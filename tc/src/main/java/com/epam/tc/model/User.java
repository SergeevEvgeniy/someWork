package com.epam.tc.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractEntity implements Serializable {

    private String login;
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role")
    private UserRole userRole;

    public void setUserRole(String userRole) {
        this.userRole.setRole(userRole);
    }

    public String getUserRole() {
        return userRole.getRole();
    }

    public User(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public User() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
