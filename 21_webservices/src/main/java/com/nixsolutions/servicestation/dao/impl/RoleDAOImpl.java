package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.RoleDAO;

import com.nixsolutions.servicestation.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
@Repository("roleDao")
public class RoleDAOImpl extends GenericAbstractDAO<Role> implements RoleDAO {
}
