package com.nixsolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.hibernate.entity.Status;

@Service
public class StatusService {

	@Autowired
	private StatusDAO statusDao;

	public List<Status> getAllStatuses() {
		return statusDao.getAll();
	}
	
	public Status getStatusById(int id) {
		return statusDao.getByPK(id);
	}
}
