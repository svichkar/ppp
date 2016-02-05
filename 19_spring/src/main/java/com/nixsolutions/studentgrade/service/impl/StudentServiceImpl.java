package com.nixsolutions.studentgrade.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.dao.StudentDAO;
import com.nixsolutions.studentgrade.dao.StudentGroupDAO;
import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDao;
	
	@Autowired
	StudentGroupDAO groupDao;
	
	@Autowired
    StatusDAO statusDao;
	
	@Autowired
    TermDAO termDao;

	@Override
	public Long createStudent (String firstName, String lastName, Long groupId, Date admissionDate, Long statusId, Long termId) {
		Student newStudent = new Student();
		newStudent.setFirstName(firstName);
		newStudent.setLastName(lastName);
		newStudent.setGroup(groupDao.findStudentGroupById(groupId));
		newStudent.setAdmissionDate(admissionDate);
		newStudent.setStatus(statusDao.findStatusById(statusId));
		newStudent.setTerm(termDao.findTermById(termId));
		studentDao.createStudent(newStudent);
		return newStudent.getStudentId();
    }

	@Override
	public void updateStudent(Long studentId, String firstName, String lastName, Long groupId, Date admissionDate, Long statusId, Long termId) {
		Student updatedStudent = studentDao.findStudentById(studentId);
		updatedStudent.setFirstName(firstName);
		updatedStudent.setLastName(lastName);
		updatedStudent.setGroup(groupDao.findStudentGroupById(groupId));
		updatedStudent.setAdmissionDate(admissionDate);
		updatedStudent.setStatus(statusDao.findStatusById(statusId));
		updatedStudent.setTerm(termDao.findTermById(termId));
		studentDao.updateStudent(updatedStudent);
	}

	@Override
	public void deleteStudent(Long studentId) {
		studentDao.deleteStudent(studentDao.findStudentById(studentId));
	}

	@Override
	public Student findStudentById(Long studentId) {
		return studentDao.findStudentById(studentId);
	}

	@Override
	public List<Student> findAllStudents() {
		return studentDao.findAllStudents();
	}

	@Override
	public List<Student> findStudentsByLastNameAndGroupId(String lastName, Long groupId) {
		return studentDao.findStudentsByLastNameAndGroupId(lastName, groupId);
	}

	@Override
	public List<Student> findStudentsByLastName(String lastName) {
		return studentDao.findStudentsByLastName(lastName);
	}

	@Override
	public List<Student> findStudentsByGroupId(Long groupId) {
		return studentDao.findStudentsByGroupId(groupId);
	}


}
