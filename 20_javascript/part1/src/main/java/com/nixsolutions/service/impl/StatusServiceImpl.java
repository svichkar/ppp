package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.entities.Status;
import com.nixsolutions.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDAO statusDaoImpl;

	@Override
	public Status getStatusById(long id) {
		return statusDaoImpl.findByPK(id);
	}

	@Override
	public List<Status> getAllStatuses() {
		return statusDaoImpl.getAll();
	}

	@Override
	public void addStatus(Status status) {
		statusDaoImpl.create(status);
	}

	@Override
	public void updateStatus(Status status) {
		statusDaoImpl.update(status);
	}

	@Override
	public void deleteStatus(Status status) {
		statusDaoImpl.delete(status);
	}

}
