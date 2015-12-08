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
}
