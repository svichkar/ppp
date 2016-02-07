package com.nixsolutions.studentgrade.webservice.rest;

import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("students")
public class StudentWebService {

    private StudentService studentService;

    @Autowired
    @Qualifier("studentsService")
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GET
    @Path("/getStudent/{studentId}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getUserById(@PathParam("studentId") Long studentId, @QueryParam("fmt") String format) {


        System.out.println(studentId);
        System.out.println(format);

        Student student = studentService.findById(studentId);

        System.out.println(student.getStudentId());

        return Response
                // Set the status and Put your entity here.
                .ok(student)
                // Add the Content-Type header to tell Jersey which format it should marshall the entity into.
                .header(HttpHeaders.CONTENT_TYPE, "json".equals(format) ? MediaType.APPLICATION_JSON : MediaType.APPLICATION_XML)
                .build();
    }

}
