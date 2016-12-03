package com.epam.tc.service.status;

import com.epam.tc.dao.status.StatusDao;
import com.epam.tc.model.Status;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusDao statusDao;

    @Override
    public List<Status> getAll() {
        return statusDao.getAll();
    }

    @Override
    public void create(Status status) {
        statusDao.create(status);
    }

    @Override
    public Status getByName(String name) {
        return statusDao.getByName(name);
    }

}
