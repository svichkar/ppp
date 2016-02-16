package com.nixsolutions.asp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rate implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rate_id")
	private int rateId;
    @Column(name = "rate_value", length = 1, nullable = false)
	private String rateValue;
	
	public Rate(){
	}

	public int getId() {		
		return rateId;
	}

	public void setId(int rateId) {
		this.rateId = rateId;
	}
	
	public String getRateValue(){
		return rateValue;
	}
	
	public void setRateValue(String rateValue){
		this.rateValue = rateValue;
	}
}
