package database.entity;

public class StudentGroup extends BaseEntity {

	private int id;
	private String studentGroupName;
	
	public StudentGroup(int studentGroupId, String studentGroupName){
		this.id = studentGroupId;
		this.studentGroupName = studentGroupName;
	}

	@Override
	public int getId() {		
		return id;
	}
	
	public String getStudentGroupName(){
		return studentGroupName;
	}
	
	public void setId(int studentGroupId) {		
		this.id = studentGroupId;
	}
	
	public void setStudentGroupName(String studentGroupName){
		this.studentGroupName = studentGroupName;
	}

}
