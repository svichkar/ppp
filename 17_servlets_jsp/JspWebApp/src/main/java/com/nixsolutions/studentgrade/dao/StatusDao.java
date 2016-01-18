package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Status;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface StatusDao {

    public boolean create(Status status);

    public boolean update(Status status);

    public boolean delete(Status status);

    public List<Status> findAll();

    public Status findById(Long id);

    public Status findByName(String status);
}
