package com.nixsolutions.studentgrade.dao;

import com.nixsolutions.studentgrade.model.Grade;

import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public interface GradeDao {

    void create(Grade grade);

    void update(Grade grade);

    void delete(Grade grade);

    List<Grade> findAll();

    Grade findById(Long id);

    Grade findByName(String gradeName);
}
