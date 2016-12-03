package com.epam.tc.dao.status;

import com.epam.tc.dao.CRUDdao;
import com.epam.tc.model.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDao extends CRUDdao<Status> {

    Status getByName(String name);
}
