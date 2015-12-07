package bean;

import java.sql.Date;

public class StudentBean {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateBirthday;
    private Date dateEntry;
    private String studentGroup;
    private String term;
    private String status;

    public StudentBean(int studentId, String firstName, String lastName, Date dateBirthday, Date dateEntry,
                       String studentGroup, String term, String status) {
        this.id = studentId;
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDateBirthday(dateBirthday);
        this.setDateEntry(dateEntry);
        this.setStudentGroup(studentGroup);
        this.setTerm(term);
        this.setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
