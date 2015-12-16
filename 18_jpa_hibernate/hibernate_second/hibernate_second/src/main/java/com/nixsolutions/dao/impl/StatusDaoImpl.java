package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entity.Status;

public class StatusDaoImpl extends AbstractDaoImpl implements StatusDao {

    @Override
    public Boolean create(Status status) {
	return super.insert(status);
    }

    @Override
    public Boolean update(Status status) {
	return super.update(status);
    }

    @Override
    public Boolean delete(Status status) {
	return super.delete(status);
    }

    @Override
    public Status getByStatusId(Integer statusId) {
	Status s = new Status();
	s.setStatusId(statusId);
	List<Status> statusList = super.findBySeveralFields(statusId, new String[]{"statusId"});
	if (statusList.size() > 0) {
	    return statusList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public Status getByStatusName(String statusName) {
	Status s = new Status();
	s.setStatusName(statusName);
	List<Status> statusList = super.findBySeveralFields(s, new String[]{"statusName"});
	if (statusList.size() > 0) {
	    return statusList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Status> getAll() {
	return super.findBySeveralFields(new Status(), null);
    }
}
