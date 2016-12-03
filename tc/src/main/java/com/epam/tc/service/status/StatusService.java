package com.epam.tc.service.status;

import com.epam.tc.model.Status;
import java.util.List;

public interface StatusService {

    List<Status> getAll();

    void create(Status status);

    Status getByName(String name);
}
