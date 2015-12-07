package com.nixsolutions.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StudentGroup implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_group_id")
	private int studentGroupId;
    @Column(name = "student_group_name", length = 256, nullable = false)
	private String studentGroupName;
	
	public StudentGroup(){
	}

	public int getStudentGroupId() {		
		return studentGroupId;
	}

	public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
	}
	
	public String getStudentGroupName(){
		return studentGroupName;
	}

	public void setStudentGroupName(String studentGroupName){
		this.studentGroupName = studentGroupName;
	}

}
