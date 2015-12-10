package com.nixsolutions.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entity.Status;
import com.nixsolutions.services.StatusBo;

@Service
public class StatusBoImpl implements StatusBo {

	@Autowired
	private StatusDao statusDao;
	
	@Override
	public void create(Status status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Status status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Status status) {
		// TODO Auto-generated method stub

	}

	@Override
	public Status getByStatusId(int statusId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status getByStatusName(String statusName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Status> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
