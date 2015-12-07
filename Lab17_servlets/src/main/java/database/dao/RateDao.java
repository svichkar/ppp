package database.dao;

import java.util.List;

import database.entities.Rate;

public interface RateDao {
	
	public void create(Character rateValue);
	
	public void update(Rate rate);
	
	public void delete(Rate rate);
	
	public Rate getByRateId(int rateId);
	
	public Rate getByRateValue(Character rateValue);
	
	public List<Rate> getAll();
}
