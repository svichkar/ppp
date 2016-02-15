package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Status;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public interface StatusService {

    void create(Status status);

    void update(Status status);

    void delete(Status status);

    List<Status> findAll();

    Status findById(Long id);

    Status findByName(String status);
}
