package com.nixsolutions.studentgrade.webservice.rest;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
@Path("students")
public class StudentWebService {

    private StudentDao studentDao;

    @Autowired
    @Qualifier("studentDao")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @POST
    @Path("/createStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(Student student) {

        studentDao.create(student);
    }

    @PUT
    @Path("/updateStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(Student student) {

        studentDao.update(student);
    }

    @DELETE
    @Path("/deleteStudent")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(Student student) {

        studentDao.delete(student);
    }

    @GET
    @Path("/getAllStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> findAll() {

        return studentDao.findAll();
    }

    @GET
    @Path("/getStudent/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student findById(@PathParam("studentId") Long studentId) {

        return studentDao.findById(studentId);
    }

    @GET
    @Path("/getStudentByNameAndLastName")
    @Produces(MediaType.APPLICATION_JSON)
    public Student findByNameAndLastName(@QueryParam("name") String name, @QueryParam("lastName") String lastName) {

        return studentDao.findByNameAndLastName(name, lastName);
    }

    @GET
    @Path("/getStudentByLastNameAndGroup")
    @Produces(MediaType.APPLICATION_JSON)
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
