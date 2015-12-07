package database.entities;

import java.sql.Date;

public class Student extends BaseEntity {

	private int studentId;
	private String firstName;
	private String lastName;
	private Date dateBirthday;
	private Date dateEntry;
	private int studentGroupId;
	private int termId;
	private int statusId;

	public Student(int studentId, String firstName, String lastName, Date dateBirthday, Date dateEntry,
			int studentGroupId, int termId, int statusId) {
		this.studentId = studentId;
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setDateBirthday(dateBirthday);
		this.setDateEntry(dateEntry);
		this.setStudentGroupId(studentGroupId);
		this.setTermId(termId);
		this.setStatusId(statusId);
	}

	@Override
	public int getId() {
		return studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateBirthday() {
		return dateBirthday;
	}

	public void setDateBirthday(Date dateBirthday) {
		this.dateBirthday = dateBirthday;
	}

	public Date getDateEntry() {
		return dateEntry;
	}

	public void setDateEntry(Date dateEntry) {
		this.dateEntry = dateEntry;
	}

	public int getStudentGroupId() {
		return studentGroupId;
	}

	public void setStudentGroupId(int studentGroupId) {
		this.studentGroupId = studentGroupId;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

}
