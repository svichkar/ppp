package com.nixsolutions.studentgrade.webservice.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nixsolutions.studentgrade.dao.TermDAO;
import com.nixsolutions.studentgrade.entity.Term;

@Component
@Path("/term")
public class TermServiceWeb {

	@Autowired
	private TermDAO termDao;
    

	@GET
	@Path("/findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Term> findAllTerms() {
		return termDao.findAllTerms();
	}
	
	@GET
	@Path("/findById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Term findTermById(@PathParam("id") Long termId) {
		return termDao.findTermById(termId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	@Path("/create")
	public Response createTerm(Term term) {
		termDao.createTerm(term);
		return Response.status(Response.Status.OK).entity(term.getTermId().toString()).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateTerm(Term term) {
		termDao.updateTerm(term);
		return Response.ok().build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response deleteTerm(Term term) {
		termDao.deleteTerm(term);
		return Response.ok().build();
	}	
	
}
