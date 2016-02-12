package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.Status;

public interface StatusService {

	public Status getStatusById(long id);

	public List<Status> getAllStatuses();

	public void addStatus(Status status);

	public void updateStatus(Status status);

	public void deleteStatus(Status status);

}
