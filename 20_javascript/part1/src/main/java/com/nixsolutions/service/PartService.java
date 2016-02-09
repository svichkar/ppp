package com.nixsolutions.service;

import java.util.List;
import com.nixsolutions.entities.Part;

public interface PartService {

	public List<Part> getAllPart();

	public Part getPartById(long id);

	public void addPart(Part part);

	public void updatePart(Part part);

	public void deletePart(Part part);

}
