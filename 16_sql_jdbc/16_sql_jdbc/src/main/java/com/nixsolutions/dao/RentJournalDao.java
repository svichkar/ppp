package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.RentJournal;

public interface RentJournalDao {
	public List<RentJournal> getAllRents();
	public RentJournal getRent(int rentId);
	public void createRent(RentJournal rent);
	public void updateRent(RentJournal rent);
	public void deleteRent(RentJournal rent);
}
