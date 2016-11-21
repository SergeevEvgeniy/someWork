package com.epam.tc.dao.evaluate;

import com.epam.tc.dao.CRUDdaoImpl;
import com.epam.tc.model.Evaluate;
import org.springframework.stereotype.Repository;

@Repository
public class EvaluateDaoImpl extends CRUDdaoImpl<Evaluate> implements EvaluateDao {

    public EvaluateDaoImpl() {
        super(Evaluate.class);
    }

}
