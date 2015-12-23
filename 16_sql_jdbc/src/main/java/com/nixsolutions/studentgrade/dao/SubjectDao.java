package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.entity.Subject;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface SubjectDao {

    public boolean create(Subject subject);

    public int update(Subject subject);

    public int delete(Subject subject);

    public List<Subject> findAll();

    public Subject findById(int id);
}
