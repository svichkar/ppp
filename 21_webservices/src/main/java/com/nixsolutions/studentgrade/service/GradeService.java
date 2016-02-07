package com.nixsolutions.studentgrade.service;

import com.nixsolutions.studentgrade.model.Grade;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
public interface GradeService {

    public void create(Grade grade);

    public void update(Grade grade);

    public void delete(Grade grade);

    public List<Grade> findAll();

    public Grade findById(Long id);

    public Grade findByName(String gradeName);
}
