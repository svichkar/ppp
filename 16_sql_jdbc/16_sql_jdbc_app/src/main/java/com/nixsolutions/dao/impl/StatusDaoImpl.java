package com.nixsolutions.dao.impl;

import java.util.List;

import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entity.Status;

public class StatusDaoImpl extends AbstractDaoImpl implements StatusDao {
    private static String TABLE_NAME = "status";

    @Override
    public void create(String statusName) {
	super.insert(new String[] { "status_name" }, new String[] { statusName }, TABLE_NAME);
    }

    @Override
    public void update(Status status) {
	super.update(status, Status.getMap(), TABLE_NAME);
    }

    @Override
    public void delete(Status status) {
	super.delete("status_id", status.getStatusId(), TABLE_NAME);
    }

    @Override
    public Status getByStatusId(int statusId) {
	List<Status> statusList = super.find("status_id", String.valueOf(statusId), Status.getMap(), Status.class);
	if (statusList.size() > 0) {
	    return statusList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public Status getByStatusName(String statusName) {
	List<Status> statusList = super.find("status_name", String.valueOf(statusName), Status.getMap(), Status.class);
	if (statusList.size() > 0) {
	    return statusList.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public List<Status> getAll() {
	return super.find(null, null, Status.getMap(), Status.class);
    }
}
