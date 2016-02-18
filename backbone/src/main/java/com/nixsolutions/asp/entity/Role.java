package com.nixsolutions.asp.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "role", propOrder = {
    "id",
    "roleName"
})
public class Role implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role_name", length = 256, nullable = false)
    @XmlElement(required = true)
    private String roleName;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int roleId) {
        this.id = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
