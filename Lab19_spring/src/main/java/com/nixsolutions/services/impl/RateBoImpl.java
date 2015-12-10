package com.nixsolutions.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.RateDao;
import com.nixsolutions.entity.Rate;
import com.nixsolutions.services.RateBo;

@Service
public class RateBoImpl implements RateBo {
	
	@Autowired
	private RateDao rateDao;

	@Override
	public void create(Rate rate) {
		rateDao.create(rate);
	}

	@Override
	public void update(Rate rate) {
		rateDao.update(rate);
	}

	@Override
	public void delete(Rate rate) {
		rateDao.delete(rate);
	}

	@Override
	public Rate getByRateId(int rateId) {
		return rateDao.getByRateId(rateId);
	}

	@Override
	public Rate getByRateValue(String rateValue) {
		return rateDao.getByRateValue(rateValue);
	}

	@Override
	public List<Rate> getAll() {
		return rateDao.getAll();
	}

}
