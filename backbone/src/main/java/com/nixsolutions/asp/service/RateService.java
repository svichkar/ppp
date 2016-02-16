package com.nixsolutions.asp.service;

import com.nixsolutions.asp.entity.Rate;

import java.util.List;

public interface RateService {
	
	void create(Rate rate);
	
	void update(Rate rate);
	
	void delete(Rate rate);
	
	Rate getByRateId(int rateId);
	
	Rate getByRateValue(String rateValue);
	
	List<Rate> getAll();
}
