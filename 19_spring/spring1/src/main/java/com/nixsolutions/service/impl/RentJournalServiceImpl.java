package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.RentJournalDao;
import com.nixsolutions.entity.RentJournal;
import com.nixsolutions.service.RentJournalService;

@Service
public class RentJournalServiceImpl implements RentJournalService{

	@Autowired
	private RentJournalDao rentJournalDao;
	
	@Override
	public List<RentJournal> getAllRents() {
		return rentJournalDao.getAllRents();
	}

	@Override
	public RentJournal getRentById(Long rentId) {
		return rentJournalDao.getRentById(rentId);
	}

	@Override
	public List<RentJournal> getRentsByClientId(Long clientId) {
		return rentJournalDao.getRentsByClientId(clientId);
	}

	@Override
	public void createRent(RentJournal rent) {
		rentJournalDao.createRent(rent);
		
	}

	@Override
	public void updateRent(RentJournal rent) {
		rentJournalDao.updateRent(rent);
	}

	@Override
	public void deleteRent(RentJournal rent) {
		rentJournalDao.deleteRent(rent);
	}

}
