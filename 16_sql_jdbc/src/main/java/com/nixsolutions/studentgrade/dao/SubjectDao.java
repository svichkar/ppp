package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Subject;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface SubjectDao {

    public Subject create(Subject subject);

    public boolean update(Subject subject);

    public boolean delete(Subject subject);

    public List<Subject> findAll();

    public Subject findById(int id);
}
