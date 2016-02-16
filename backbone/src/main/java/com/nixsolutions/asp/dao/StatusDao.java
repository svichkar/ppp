package com.nixsolutions.asp.dao;

import com.nixsolutions.asp.entity.Status;

import java.util.List;

public interface StatusDao {
	
	void create(Status status);

	void update(Status status);

	void delete(Status status);

	Status getByStatusId(int statusId);
	
	Status getByStatusName(String statusName);

	List<Status> getAll();
}
