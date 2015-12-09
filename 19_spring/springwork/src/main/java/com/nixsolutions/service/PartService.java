package com.nixsolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.hibernate.entity.Part;

@Service
public class PartService {

	@Autowired
	private PartDAO partDao;

	public List<Part> getAllParts() {
		return partDao.getAll();
	}
	
	public Part getPartById(long id) {
		return partDao.getByPK(id);
	}
	
	public void addPart(Part part) {
		partDao.createFrom(part);
	}
	
	public void updatePart(Part part) {
		partDao.update(part);
	}
	
	public void deletePart(Part part) {
		partDao.delete(part);
	}
}
