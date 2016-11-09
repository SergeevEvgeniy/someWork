package com.epam.tc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int id;

    private String email;
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subscribers")
    private Set<Course> courses = new HashSet<>(0);
    
    public Set<Course> getStocks() {
        return this.courses;
    }

    @ManyToOne
    @JoinColumn(name = "RoleId")
    private UserRole userRole;

    public void setUserRole(String userRole) {
        this.userRole.setRole(userRole);
    }

    public String getUserRole() {
        return userRole.getRole();
    }

    public User(String email, String login, String password, UserRole userRole) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
