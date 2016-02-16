package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.Status;

public interface StatusService {

	Status getStatusById(long id);

	List<Status> getAllStatuses();

	void addStatus(Status status);

	void updateStatus(Status status);

	void deleteStatus(Status status);

}
