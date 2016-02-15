package com.nixsolutions.studentgrade.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by svichkar on 2/12/2016.
 */

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllStudentsBean implements Serializable{

    @XmlElement(name = "student")
    private List<Student> students = null;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public AllStudentsBean() {
    }
}


