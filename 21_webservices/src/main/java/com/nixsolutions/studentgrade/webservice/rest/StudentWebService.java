package com.nixsolutions.studentgrade.webservice.rest;

import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("rest/students")
public class StudentWebService {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GET
    @Path("/getStudent/{studentId}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getStudentById(@PathParam("studentId") Long studentId, @QueryParam("fmt") String format) {

        Student student = studentService.findById(studentId);
        return Response
                // Set the status and Put your entity here.
                .ok(student)
                // Add the Content-Type header to tell Jersey which format it should marshall the entity into.
                .header(HttpHeaders.CONTENT_TYPE, "json".equals(format) ? MediaType.APPLICATION_JSON
                        : MediaType.APPLICATION_XML)
                .build();
    }

    @GET
    @Path("/getStudent/all")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getAllStudents(@QueryParam("fmt") String format) {

        List<Student> studentList = studentService.findAll();
        return Response
                // Set the status and Put your entity here.
                .ok(studentList)
                // Add the Content-Type header to tell Jersey which format it should marshall the entity into.
                .header(HttpHeaders.CONTENT_TYPE, "json".equals(format) ? MediaType.APPLICATION_JSON
                        : MediaType.APPLICATION_XML)
                .build();
    }
}
