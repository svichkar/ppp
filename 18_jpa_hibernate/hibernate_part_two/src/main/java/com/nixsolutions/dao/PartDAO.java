package com.nixsolutions.dao;

import com.nixsolutions.hibernate.entity.Part;

public interface PartDAO extends GenericDAO<Part> {
	
	Part getByPK(long id);
}
