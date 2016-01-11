package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Status;

public interface StatusDAO {

	public void createStatus(Status status);

	public void updateStatus(Status status);

	public void deleteStatus(Status status);

	public Status findStatusById(int statusId);
	
	public List<Status> findAllStatuses();
}
