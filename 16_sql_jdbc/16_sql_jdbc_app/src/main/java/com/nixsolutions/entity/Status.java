package com.nixsolutions.entity;

import java.util.HashMap;
import java.util.Map;

public class Status {
    private int statusId;
    private String statusName;
    private static Map<String, String> mapColNames;

    public Status() {
    }

    public Status(int statusId, String statusName) {
	this.statusId = statusId;
	this.statusName = statusName;
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_statusId", "status_id");
	    mapColNames.put("statusName", "status_name");
	}
    }

    public int getStatusId() {
	return statusId;
    }

    public void setStatusId(int statusId) {
	this.statusId = statusId;
    }

    public String getStatusName() {
	return statusName;
    }

    public void setStatusName(String statusName) {
	this.statusName = statusName;
    }

    public static Map<String, String> getMap() {
	if (mapColNames == null) {
	    mapColNames = new HashMap<String, String>();
	    mapColNames.put("PK_statusId", "status_id");
	    mapColNames.put("statusName", "status_name");
	}
	return mapColNames;
    }
}
