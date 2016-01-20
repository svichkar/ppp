package com.nixsolutions.studentgrade.dao;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Status;

public interface StatusDAO {

	public void createStatus(Status status);

	public void updateStatus(Status status);

	public void deleteStatus(Status status);

	public Status findStatusById(int statusId);
	
	public List<Status> findAllStatuses();
}
