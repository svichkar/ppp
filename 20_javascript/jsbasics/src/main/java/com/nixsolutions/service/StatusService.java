package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.hibernate.entity.Status;

public interface StatusService {

	List<Status> getAllStatuses();

	Status getStatusById(int id);
}
