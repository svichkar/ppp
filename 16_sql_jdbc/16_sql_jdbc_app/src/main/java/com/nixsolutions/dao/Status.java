package com.nixsolutions.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Status extends AbstractInstance{
    private int statusId;
    private String statusName;

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
    
    public List select(String[] fields, String condition) throws ClassNotFoundException, IOException, SQLException{
	List<Status> termResult = new ArrayList<Status>();
	ResultSet result = super.find(fields, condition);
	List<String> colNames = new ArrayList<String>();
	for (int i = 1; i <= result.getMetaData().getColumnCount(); i++){
	    colNames.add(result.getMetaData().getColumnName(i).toLowerCase());
	}
	while (result.next()){
	    Status s = new Status();
	    if (colNames.contains("status_id")){
		s.statusId = result.getInt("status_id");
	    }
	    if (colNames.contains("status_name")){
		s.statusName = result.getString("status_name");
	    }
	    termResult.add(s);
	}
	return termResult;
    }
}
