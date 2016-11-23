package com.epam.tc.service.evaluate;

import com.epam.tc.dao.evaluate.EvaluateDao;
import com.epam.tc.model.Course;
import com.epam.tc.model.Evaluate;
import com.epam.tc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateDao evaluateDao;

    @Override
    public void setGrade(Course course, User attender, int grade) {
        Evaluate evaluate = evaluateDao.getByCourseAndUser(course, attender);
        evaluate.setGrade(grade);
        evaluateDao.update(evaluate);
    }
}
