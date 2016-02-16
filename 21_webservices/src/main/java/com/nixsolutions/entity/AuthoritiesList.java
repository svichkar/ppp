package com.nixsolutions.entity;

import java.util.ArrayList;
import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
 
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "authoritiesListType")
@XmlRootElement(name = "authoritiesList")
public class AuthoritiesList {
    private List<SimpleGrantedAuthority> authorities = new ArrayList<>();
 
    public AuthoritiesList() {}
 
    public AuthoritiesList(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
 
    @XmlElement(name = "authorities")
    public List<SimpleGrantedAuthority> getUsers() {
        return authorities;
    }
 
    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}