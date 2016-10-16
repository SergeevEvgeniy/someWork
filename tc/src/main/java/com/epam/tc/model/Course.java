package com.epam.tc.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course extends AbstractEntity {

    private String name;

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
}
