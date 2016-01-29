package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.RoleDao;
import com.nixsolutions.studentgrade.model.Role;
import com.nixsolutions.studentgrade.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    @Qualifier("roleDao")
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void create(Role role) {

        roleDao.create(role);
    }

    @Override
    public void update(Role role) {

        roleDao.update(role);
    }

    @Override
    public void delete(Role role) {

        roleDao.delete(role);
    }

    @Override
    public List<Role> findAll() {

        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id) {

        return roleDao.findById(id);
    }

    @Override
    public Role findByName(String role) {

        return roleDao.findByName(role);
    }
}
