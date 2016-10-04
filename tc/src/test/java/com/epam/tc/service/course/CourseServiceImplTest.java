package com.epam.tc.service.course;

import com.epam.tc.model.Course;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

@Ignore
public class CourseServiceImplTest {

    public CourseServiceImplTest() {
    }

    /**
     * Test of create method, of class CourseServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Course course = new Course("TestCourse");
        CourseServiceImpl instance = new CourseServiceImpl();
        instance.create(course);

        System.out.println(course.getId() + " " + course.getName());
    }

    /**
     * Test of getById method, of class CourseServiceImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        CourseServiceImpl instance = new CourseServiceImpl();
        Course expResult = null;
        Course result = instance.getById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class CourseServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("create");
        Course course = new Course("TestCourse");
        CourseServiceImpl instance = new CourseServiceImpl();
        instance.create(course);
        System.out.println("created!");
        instance.delete(course);
        System.out.println("deleted!");
    }
}
