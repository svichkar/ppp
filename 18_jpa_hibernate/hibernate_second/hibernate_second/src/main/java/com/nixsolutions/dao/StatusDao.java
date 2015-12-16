package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Status;

public interface StatusDao {
    
    public Boolean create(Status status);

    public Boolean update(Status status);

    public Boolean delete(Status status);

    public Status getByStatusId(Integer statusId);

    public Status getByStatusName(String statusName);

    public List<Status> getAll();
}
