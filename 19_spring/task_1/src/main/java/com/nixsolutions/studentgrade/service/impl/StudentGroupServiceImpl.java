package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.StudentGroupDao;
import com.nixsolutions.studentgrade.model.StudentGroup;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by konstantin on 1/28/2016.
 */
public class StudentGroupServiceImpl implements StudentGroupService {

    private StudentGroupDao groupDao;

    @Autowired
    @Qualifier("groupDao")
    public void setGroupDao(StudentGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void create(StudentGroup group) {

        groupDao.create(group);
    }

    @Override
    public void update(StudentGroup group) {

        groupDao.update(group);
    }

    @Override
    public void delete(StudentGroup group) {

        groupDao.delete(group);
    }

    @Override
    public List<StudentGroup> findAll() {

        return groupDao.findAll();
    }

    @Override
    public StudentGroup findById(Long id) {

        return groupDao.findById(id);
    }

    @Override
    public StudentGroup findByName(String group) {

        return groupDao.findByName(group);
    }
}
