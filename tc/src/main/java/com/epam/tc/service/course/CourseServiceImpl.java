package com.epam.tc.service.course;

import com.epam.tc.model.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.tc.dao.CourseDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public void create(Course course) {
        courseDao.create(course);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public Course getById(int id) {
        return courseDao.getById(id);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public void delete(Course course) {
        courseDao.delete(course);
    }

    @Override
    public void SetupDefCourses() {
        Course course = new Course("Draft");
        this.create(course);
        course = new Course("Proposal");
        this.create(course);
        course = new Course("Rejected");
        this.create(course);
        course = new Course("New");
        this.create(course);
        course = new Course("Open");
        this.create(course);
        course = new Course("Ready");
        this.create(course);
        course = new Course("In Progress");
        this.create(course);
        course = new Course("Finished");
        this.create(course);
    }
}
