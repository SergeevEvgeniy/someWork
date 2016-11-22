package com.epam.tc.service.course;

import com.epam.tc.model.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.tc.dao.course.CourseDao;
import com.epam.tc.model.User;
import java.util.ArrayList;
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
    public List<Course> getUserCoursesList(User user) {
        List<Course> courses = getAll();
        List<Course> resultList = new ArrayList<>();
        courses.stream().filter((course)
                -> (course.isAttended(user) || course.isSubscribed(user) || course.getOwner() == user))
                .forEach((course) -> {
                    resultList.add(course);
                });
        return resultList;
    }

    @Override
    public List<Course> filteredCourseList(List<Course> courses, String filterCondition) {
        if (!"All".equals(filterCondition)) {
            for (Course course : courses) {
                if (!course.getCategory().getName().equals(filterCondition)) {
                    courses.remove(course);
                }
            }
        }
        return courses;
    }
}
