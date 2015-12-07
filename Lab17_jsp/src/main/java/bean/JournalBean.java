package bean;

public class JournalBean {

    private String firstName;
    private String lastName;
    private String studentGroup;
    private String subject;
    private String rate;

    public JournalBean(String firstName, String lastName, String studentGroup, String subject, String rate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentGroup = studentGroup;
        this.subject = subject;
        this.rate = rate;
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

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
