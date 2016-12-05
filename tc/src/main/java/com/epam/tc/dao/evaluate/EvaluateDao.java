package com.epam.tc.dao.evaluate;

import com.epam.tc.dao.CRUDdao;
import com.epam.tc.model.Course;
import com.epam.tc.model.Evaluate;
import com.epam.tc.model.User;

public interface EvaluateDao extends CRUDdao<Evaluate> {

    Evaluate getByCourseAndUser(Course course, User user);
}
