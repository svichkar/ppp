package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.GradeDao;
import com.nixsolutions.studentgrade.model.Grade;
import com.nixsolutions.studentgrade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {

    private GradeDao gradeDao;

    @Autowired
    @Qualifier("gradeDao")
    public void setGradeDao(GradeDao gradeDao) {
        this.gradeDao = gradeDao;
    }

    @Override
    public void create(Grade grade) {

        gradeDao.create(grade);
    }

    @Override
    public void update(Grade grade) {

        gradeDao.update(grade);
    }

    @Override
    public void delete(Grade grade) {

        gradeDao.delete(grade);
    }

    @Override
    public List<Grade> findAll() {

        return gradeDao.findAll();
    }

    @Override
    public Grade findById(Long id) {

        return gradeDao.findById(id);
    }

    @Override
    public Grade findByName(String gradeName) {

        return gradeDao.findByName(gradeName);
    }
}
