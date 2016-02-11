package com.nixsolutions.studentgrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.studentgrade.dao.StatusDAO;
import com.nixsolutions.studentgrade.entity.Status;
import com.nixsolutions.studentgrade.service.StatusService;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

	@Autowired
	StatusDAO statusDao;

	@Override
	public void createStatus(Status status) {
		statusDao.createStatus(status);
	}

	@Override
	public void updateStatus(Status status) {
		statusDao.updateStatus(status);
	}

	@Override
	public void deleteStatus(Status status) {
		statusDao.deleteStatus(status);
	}

	@Override
	public Status findStatusById(Long statusId) {
		return statusDao.findStatusById(statusId);
	}

	@Override
	public List<Status> findAllStatuses() {
		return statusDao.findAllStatuses();
	}

}
