package com.nixsolutions.asp.dao;

import java.util.List;

import com.nixsolutions.asp.entity.Rate;

public interface RateDao {
	
	void create(Rate rate);
	
	void update(Rate rate);
	
	void delete(Rate rate);
	
	Rate getByRateId(int rateId);
	
	Rate getByRateValue(String rateValue);
	
	List<Rate> getAll();
}
