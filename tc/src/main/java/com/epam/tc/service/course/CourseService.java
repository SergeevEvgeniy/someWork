/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.service.course;

import com.epam.tc.model.Course;
import java.util.List;

/**
 *
 * @author Yauheni_Siarheyeu
 */
public interface CourseService {

    void create(Course course);

    void delete(Course course);

    List<Course> getAll();

    Course getById(int id);

    void update(Course course);
    
}
