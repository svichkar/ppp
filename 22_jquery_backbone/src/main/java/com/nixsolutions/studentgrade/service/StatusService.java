package com.nixsolutions.studentgrade.service;

import java.util.List;

import com.nixsolutions.studentgrade.entity.Status;

public interface StatusService {

	public void createStatus(Status status);

	public void updateStatus(Status status);

	public void deleteStatus(Status status);

	public Status findStatusById(Long statusId);
	
	public List<Status> findAllStatuses();
}
