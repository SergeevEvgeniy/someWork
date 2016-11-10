package com.epam.tc.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId")
    private int id;
    private String name;
    private String description;
    private String links;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Course_User", joinColumns = {
        @JoinColumn(name = "CourseId")}, inverseJoinColumns = {
        @JoinColumn(name = "UserId")})
    private Set<User> subscribers;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User owner;

    public Set<User> getSubscribers() {
        return this.subscribers;
    }

    public void addSubscriber(User subscriber) {
        this.subscribers.add(subscriber);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, String description, String links, User user) {
        this.name = name;
        this.description = description;
        this.links = links;
        this.owner = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getLinks() {
        return links;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
