package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.RoleDAO;
import com.nixsolutions.servicestation.entity.Role;
import com.nixsolutions.servicestation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by rybkinrolla on 06.01.2016.
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;

    @Override
    public void create(Role entity) {
        roleDAO.create(entity);
    }

    @Override
    public void update(Role entity) {
        roleDAO.update(entity);
    }

    @Override
    public void delete(Role entity) {
        roleDAO.delete(entity);
    }

    @Override
    public Role findById(Long id) {
        Role role = roleDAO.findById(id);
        return role;
    }

    @Override
    public Set<Role> findAll() {
        Set<Role> roleSet = roleDAO.findAll();
        return roleSet;
    }
}
