package com.nixsolutions.studentgrade.webservice.rest;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
@Path("students")
public class StudentWebService implements StudentService {

    private StudentDao studentDao;

    @Autowired
    @Qualifier("studentDao")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @POST
    @Path("/createStudent")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public void create(Student student) {

        studentDao.create(student);
    }

    @PUT
    @Path("/updateStudent")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public void update(Student student) {

        studentDao.update(student);
    }

    @DELETE
    @Path("/deleteStudent")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public void delete(Student student) {

        studentDao.delete(student);
    }

    @GET
    @Path("/getAllStudents")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public List<Student> findAll() {

        return studentDao.findAll();
    }

    @GET
    @Path("/getStudent/{studentId}")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public Student findById(@PathParam("studentId") Long id) {

        return studentDao.findById(id);
    }

    @GET
    @Path("/getStudentByNameAndLastName")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public Student findByNameAndLastName(@QueryParam("name") String name, @QueryParam("lastName") String lastName) {

        return studentDao.findByNameAndLastName(name, lastName);
    }

    @GET
    @Path("/getStudentByLastNameAndGroup")
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public List<Student> findByLastNameAndGroup(@QueryParam("lastName") String lastName, @QueryParam("groupName") String groupName) {

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
