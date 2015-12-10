package com.nixsolutions.services;

import com.nixsolutions.entity.Rate;

import java.util.List;

public interface RateBo {
	
	public void create(Rate rate);
	
	public void update(Rate rate);
	
	public void delete(Rate rate);
	
	public Rate getByRateId(int rateId);
	
	public Rate getByRateValue(String rateValue);
	
	public List<Rate> getAll();
}
