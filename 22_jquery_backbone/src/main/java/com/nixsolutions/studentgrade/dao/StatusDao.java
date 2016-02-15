package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Status;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface StatusDao {

    void create(Status status);

    void update(Status status);

    void delete(Status status);

    List<Status> findAll();

    Status findById(Long id);

    Status findByName(String status);
}
