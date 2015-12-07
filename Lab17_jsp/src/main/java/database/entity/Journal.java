package database.entity;

public class Journal extends BaseEntity {
	private int id;
	private int studentId;
	private int subjectId;
	private int rateId;
	
	public Journal(int journalId, int studentId, int subjectId, int rate){
		this.id = journalId;
		this.setStudentId(studentId);
		this.setSubjectId(subjectId);
		this.setRateId(rate);
	}

	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int journalId) {		
		this.id = journalId;
	}
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getRateId() {
		return rateId;
	}

	public void setRateId(int rateId) {
		this.rateId = rateId;
	}	
}
