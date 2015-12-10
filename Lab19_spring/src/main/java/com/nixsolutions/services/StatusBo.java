package com.nixsolutions.services;

import com.nixsolutions.entity.Status;

import java.util.List;

public interface StatusBo {
	public void create(Status status);

	public void update(Status status);

	public void delete(Status status);

	public Status getByStatusId(int statusId);
	
	public Status getByStatusName(String statusName);

	public List<Status> getAll();
}
