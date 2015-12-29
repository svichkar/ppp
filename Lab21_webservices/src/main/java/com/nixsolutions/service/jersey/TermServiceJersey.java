package com.nixsolutions.service.jersey;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nixsolutions.dao.TermDao;
import com.nixsolutions.entity.Term;
import com.nixsolutions.service.TermService;

@Singleton
@Component
@Path("/term")
public class TermServiceJersey {

	@Autowired
	private TermDao termDao;
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/add")
	public void create(Term term) {
		termDao.create(term);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/update")
	public void update(Term term) {
		termDao.update(term);
	}

	@POST
	@Path("/delete")
	@Consumes({MediaType.APPLICATION_XML})
	public void delete(Term term) {
		termDao.delete(term);
	}

	@GET
	@Path("/getById")
	@Produces({ MediaType.APPLICATION_XML })
	public Term getByTermId(@QueryParam("termId") String termId1) {
		int termId = Integer.parseInt(termId1);
		return termDao.getByTermId(termId);
	}

	@GET
	@Path("/getByName")
	@Produces({ MediaType.APPLICATION_XML })
	public Term getByTermAlias(@QueryParam("alias") String alias) {
		return termDao.getByTermAlias(alias);
	}

	@GET
	@Path("/getAll")
	@Produces({ MediaType.APPLICATION_XML })
	public List<Term> getAll() {
		return termDao.getAll();
	}
}
