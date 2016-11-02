package com.epam.tc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
