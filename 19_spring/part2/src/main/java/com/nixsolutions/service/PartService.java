package com.nixsolutions.service;

import java.util.List;
import com.nixsolutions.entities.Part;

public interface PartService {

	List<Part> getAllPart();

	Part getPartById(long id);

	void addPart(Part part);

	void updatePart(Part part);

	void deletePart(Part part);

}
