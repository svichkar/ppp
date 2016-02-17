package com.nixsolutions.studentgrade.webservice.rest;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.model.Status;
import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.model.StudentGroup;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.StatusService;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import com.nixsolutions.studentgrade.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
@Path("studentService")
public class StudentWebService {

    private StudentDao studentDao;

    @Autowired
    @Qualifier("studentDao")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    @Autowired
    TermService termService;
    @Autowired
    StudentGroupService groupService;
    @Autowired
    StatusService statusService;

    @POST
    @Path("/student")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(Student student) {

        studentDao.create(student);
    }

    @PUT
    @Path("/student/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(Student student, @PathParam("id") Long id) {

        studentDao.update(student);
    }

    @DELETE
    @Path("/student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Long id) {

        studentDao.delete(studentDao.findById(id));
    }

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> findAll() {

        return studentDao.findAll();
    }

    @GET
    @Path("/student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student findById(@PathParam("id") Long id) {

        return studentDao.findById(id);
    }

    @GET
    @Path("/student/{firstName}/{lastName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student findByNameAndLastName(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {

        return studentDao.findByNameAndLastName(firstName, lastName);
    }

    @GET
    @Path("/student")
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


    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Status> findAllStatus() {

        return statusService.findAll();
    }

    @GET
    @Path("/group")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentGroup> findAllGroups() {

        return groupService.findAll();
    }

    @GET
    @Path("/term")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Term> findAllTerms() {

        return termService.findAll();
    }
}
