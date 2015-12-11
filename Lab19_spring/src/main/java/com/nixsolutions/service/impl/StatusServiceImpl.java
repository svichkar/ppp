package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entity.Status;
import com.nixsolutions.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;

	@Override
	public void create(Status status) {
		statusDao.create(status);
	}

	@Override
	public void update(Status status) {
		statusDao.update(status);
	}

	@Override
	public void delete(Status status) {
		statusDao.delete(status);
	}

	@Override
	public Status getByStatusId(int statusId) {
		return statusDao.getByStatusId(statusId);
	}

	@Override
	public Status getByStatusName(String statusName) {
		return statusDao.getByStatusName(statusName);
	}

	@Override
	public List<Status> getAll() {
		return statusDao.getAll();
	}
}
