package com.nixsolutions.entity;

public class Rate extends BaseEntity {
	private int rateId;
	private Character rateValue;
	
	public Rate(int rateId, Character rateValue){
		this.rateId = rateId;
		this.rateValue = rateValue;
	}

	@Override
	public int getId() {		
		return rateId;
	}
	
	public Character getRateValue(){
		return rateValue;
	}
	
	public void setId(int rateId) {		
		this.rateId = rateId;
	}
	
	public void setStatusName(Character rateValue){
		this.rateValue = rateValue;
	}
}
