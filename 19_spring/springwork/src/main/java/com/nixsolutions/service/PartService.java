package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.hibernate.entity.Part;

public interface PartService {

	List<Part> getAllParts();

	Part getPartById(long id);

	void addPart(Part part);

	void updatePart(Part part);

	void deletePart(Part part);
}
