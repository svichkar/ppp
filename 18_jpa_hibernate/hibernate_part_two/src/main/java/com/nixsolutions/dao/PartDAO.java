package com.nixsolutions.dao;

import com.nixsolutions.hibernate.entity.Part;

public interface PartDAO extends GenericDAO<Part> {
	
	public Part getByPK(long id);
}
