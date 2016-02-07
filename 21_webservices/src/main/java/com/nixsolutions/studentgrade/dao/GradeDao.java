package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Grade;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface GradeDao {

    public void create(Grade grade);

    public void update(Grade grade);

    public void delete(Grade grade);

    public List<Grade> findAll();

    public Grade findById(Long id);

    public Grade findByName(String gradeName);
}
