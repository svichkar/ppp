package com.nixsolutions.entity;

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
	private Integer rateId;
    @Column(name = "rate_value", length = 1, nullable = false)
	private String rateValue;
	
	public Rate(){
	}

	public Integer getId() {		
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
