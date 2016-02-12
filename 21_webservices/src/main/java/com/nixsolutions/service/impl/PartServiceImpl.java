package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.entities.Part;
import com.nixsolutions.service.PartService;

@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private PartDAO partDaoImpl;

	@Override
	public List<Part> getAllPart() {
		return partDaoImpl.getAll();
	}

	@Override
	public Part getPartById(long id) {
		return partDaoImpl.findByPK(id);
	}

	@Override
	public void addPart(Part part) {
		partDaoImpl.create(part);
	}

	@Override
	public void updatePart(Part part) {
		partDaoImpl.update(part);
	}

	@Override
	public void deletePart(Part part) {
		partDaoImpl.delete(part);
	}

}
