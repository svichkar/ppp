package com.nixsolutions.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGroup extends AbstractInstance{
    private int studentGroupId;
    private String groupName;

    public int getStudentGroupId() {
	return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
	this.studentGroupId = studentGroupId;
    }

    public String getGroupName() {
	return groupName;
    }

    public void setGroupName(String groupName) {
	this.groupName = groupName;
    }
    
    public List select(String[] fields, String condition) throws ClassNotFoundException, IOException, SQLException{
	List<StudentGroup> termResult = new ArrayList<StudentGroup>();
	ResultSet result = super.find(fields, condition);
	List<String> colNames = new ArrayList<String>();
	for (int i = 1; i <= result.getMetaData().getColumnCount(); i++){
	    colNames.add(result.getMetaData().getColumnName(i).toLowerCase());
	}
	while (result.next()){
	    StudentGroup sg = new StudentGroup();
	    if (colNames.contains("student_group_id")){
		sg.studentGroupId = result.getInt("student_group_id");
	    }
	    if (colNames.contains("group_name")){
		sg.groupName = result.getString("group_name");
	    }
	    termResult.add(sg);
	}
	return termResult;
    }
}
