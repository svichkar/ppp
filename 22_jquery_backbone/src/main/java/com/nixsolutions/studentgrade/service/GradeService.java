package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Grade;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public interface GradeService {

    void create(Grade grade);

    void update(Grade grade);

    void delete(Grade grade);

    List<Grade> findAll();

    Grade findById(Long id);

    Grade findByName(String gradeName);
}
