package com.epam.tc.service.course;

import com.epam.tc.model.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.tc.dao.course.CourseDao;
import com.epam.tc.model.Category;
import com.epam.tc.model.User;
import com.epam.tc.service.status.StatusService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private StatusService statusService;

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

    @Override
    public void statusProvider(Course course) {
        if ((course.getDecision().getDm_decision().equals("Approve"))
                && ((course.getDecision().getKm_decision().equals("Approve")))) {
            course.setStatus(statusService.getByName("New"));
        }
        if ((course.getDecision().getDm_decision().equals("Reject"))
                || ((course.getDecision().getKm_decision().equals("Reject")))) {
            course.setStatus(statusService.getByName("Rejected"));
        }
        courseDao.update(course);
    }
}
