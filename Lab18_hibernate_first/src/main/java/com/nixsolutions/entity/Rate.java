package com.nixsolutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Rate implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "rate_id")
	private int rateId;
    @Column(name = "rate_value", length = 1, nullable = false)
	private String rateValue;
	
	public Rate(){
	}

	public int getId() {		
		return rateId;
	}
	
	public String getRateValue(){
		return rateValue;
	}
	
	public void setStatusName(String rateValue){
		this.rateValue = rateValue;
	}
}
