package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 1/29/2016.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Autowired
    @Qualifier("studentDao")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student student) {

        studentDao.create(student);
    }

    @Override
    public void update(Student student) {

        studentDao.update(student);
    }

    @Override
    public void delete(Student student) {

        studentDao.delete(student);
    }

    @Override
    public List<Student> findAll() {

        return studentDao.findAll();
    }

    @Override
    public Student findById(Long id) {

        return studentDao.findById(id);
    }

    @Override
    public Student findByNameAndLastName(String name, String lastName) {

        return studentDao.findByNameAndLastName(name, lastName);
    }

    @Override
    public List<Student> findByLastNameAndGroup(String lastName, String groupName) {

        List<Student> result = new ArrayList<>();

        if (lastName != null && !lastName.isEmpty()) {

            if (groupName != null && !groupName.isEmpty()) {
                result = studentDao.findByLastNameAndGroup(lastName, groupName);

            } else {

                result = studentDao.findByLastName(lastName);
            }

        } else {
            if (groupName != null && !groupName.isEmpty()) {

                result = studentDao.findByGroup(groupName);
            }
        }

        return result;
    }
}
