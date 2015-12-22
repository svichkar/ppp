/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.PartDao;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.PartOrder;
import com.nixsolutions.service.PartOrderService;
import com.nixsolutions.service.PartService;

/**
 * @author mixeyes
 *
 */
@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private PartDao partDao;
	@Autowired
	private PartOrderService partOrderService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartService#getAllParts()
	 */
	@Override
	public List<Part> getAllParts() {
		return partDao.getAllParts();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartService#getPart(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Part getPart(String partName, String vendor) {
		return partDao.getPart(partName, vendor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartService#addNewPart(java.lang.String,
	 * java.lang.String, java.lang.Integer)
	 */
	@Override
	public void addNewPart(String partName, String vendor, Integer amount) {
		partDao.addNewPart(partName, vendor, amount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.PartService#deletePartByID(java.lang.Integer)
	 */
	@Override
	public boolean deletePartByID(Integer part_id) {
		return partDao.deletePartByID(part_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.PartService#updateExistingPart(com.nixsolutions.
	 * entity.Part)
	 */
	@Override
	public void updateExistingPart(Part part) {
		partDao.updateExistingPart(part);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartService#getPart(java.lang.Integer)
	 */
	@Override
	public Part getPart(Integer part_id) {
		return partDao.getPart(part_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.PartService#getPart(java.lang.Integer)
	 */
	@Override
	public Part getPart(String part_id) {
		return getPart(Integer.decode(part_id));
	}

	@Override
	public void changeAmount(String partId, String newAmount) {
		Part part = getPart(Integer.decode(partId));
		part.setAmount(part.getAmount() - Integer.decode(newAmount));
		partDao.updateExistingPart(part);

	}

	@Override
	public void changeAmount(String orderId, String partId, String newAmount) {
		if (newAmount == null || newAmount.isEmpty())
			newAmount = "0";
		PartOrder partOrder = partOrderService.getPartByOrderId(Integer.decode(orderId), Integer.decode(partId));
		Part part = getPart(Integer.decode(partId));
		part.setAmount(part.getAmount() - (Integer.decode(newAmount) - partOrder.getAmount()));
		partDao.updateExistingPart(part);

	}
}
