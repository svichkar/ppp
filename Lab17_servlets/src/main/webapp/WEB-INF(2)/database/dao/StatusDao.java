package database.dao;

import java.util.List;

import database.entities.Status;

public interface StatusDao {
	public void create(String statusName);

	public void update(Status status);

	public void delete(Status status);

	public Status getByStatusId(int statusId);
	
	public Status getByStatusName(String statusName);

	public List<Status> getAll();
}
