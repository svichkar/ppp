package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Status;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public interface StatusService {

    public void create(Status status);

    public void update(Status status);

    public void delete(Status status);

    public List<Status> findAll();

    public Status findById(Long id);

    public Status findByName(String status);
}
