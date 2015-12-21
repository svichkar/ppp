package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.hibernate.entity.Status;
import com.nixsolutions.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDAO statusDao;

	public List<Status> getAllStatuses() {
		return statusDao.getAll();
	}

	public Status getStatusById(int id) {
		return statusDao.getByPK(id);
	}
}
