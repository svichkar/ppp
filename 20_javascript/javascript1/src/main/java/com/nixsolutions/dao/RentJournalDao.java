package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.RentJournal;

public interface RentJournalDao {
	 List<RentJournal> getAllRents();
	 RentJournal getRentById(Long rentId);
	 List<RentJournal> getRentsByClientId(Long clientId);
	 void createRent(RentJournal rent);
	 void updateRent(RentJournal rent);
	 void deleteRent(RentJournal rent);
}
