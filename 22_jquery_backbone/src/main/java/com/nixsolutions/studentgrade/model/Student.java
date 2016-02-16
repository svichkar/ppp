package com.nixsolutions.studentgrade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nixsolutions.studentgrade.webservice.adapter.SqlDateAdapter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "student")
@XmlRootElement
public class Student implements Serializable {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long studentId;

    @Column(name = "first_name", nullable = false, length = 256)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 256)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    @Cascade(CascadeType.DETACH)
    private StudentGroup studentGroup;

    @Column(name = "admission_date", nullable = false)
    private Date admissionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    @Cascade(CascadeType.DETACH)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "term_id", nullable = false)
    @Cascade(CascadeType.DETACH)
    private Term term;

    public Student() {
    }

    @XmlAttribute
    @JsonProperty("id")
    public Long getStudentId() {
        return studentId;
    }

    @JsonProperty("id")
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    @JsonIgnore
    public boolean isEmpty() {

        boolean result = false;
        if (this.studentId == null
                && this.firstName == null
                && this.lastName == null
                && this.admissionDate == null
                && this.term == null
                && this.status == null
                && this.studentGroup == null)
            result = true;

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!studentId.equals(student.studentId)) return false;
        if (!firstName.equals(student.firstName)) return false;
        if (!lastName.equals(student.lastName)) return false;
        if (!studentGroup.equals(student.studentGroup)) return false;
        if (!admissionDate.equals(student.admissionDate)) return false;
        if (!status.equals(student.status)) return false;
        return term.equals(student.term);
    }

    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + studentGroup.hashCode();
        result = 31 * result + admissionDate.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + term.hashCode();
        return result;
    }

}
