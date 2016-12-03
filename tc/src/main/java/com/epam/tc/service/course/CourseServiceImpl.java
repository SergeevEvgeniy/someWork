package com.epam.tc.service.course;

import com.epam.tc.model.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.tc.dao.course.CourseDao;
import com.epam.tc.model.Category;
import com.epam.tc.model.User;
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
    public List<Course> getAll(Category filterCategory) {
        if ((filterCategory.getName().equals("All"))) {
            return courseDao.getAll();
        } else {
            return courseDao.getByCategory(filterCategory);
        }
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
    public void addSubscriber(int courseId, User subscriber) {
        Course course = getById(courseId);
        course.addSubscriber(subscriber);
        courseDao.update(course);
    }

    @Override
    public void addAttender(int courseId, User attender) {
        Course course = getById(courseId);
        course.addAttender(attender);
        course.deleteSubscriber(attender);
        courseDao.update(course);
    }

    @Override
    public List<Course> getUserCourses(User user, Category category) {
        return courseDao.getUserCourses(user, category);
    }
}
