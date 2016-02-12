package com.nixsolutions.asp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.asp.dao.RoleDao;
import com.nixsolutions.asp.entity.Role;
import com.nixsolutions.asp.service.RoleService;

@Service
@Path("role")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

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
	public Role getByRoleId(int roleId) {
		return roleDao.getByRoleId(roleId);
	}

	@Override
	public Role getByRoleName(String roleName) {
		return roleDao.getByRoleName(roleName);
	}

	@Override
	public List<Role> getAll() {		
		return roleDao.getAll();
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Map<String, String> getRoleMap(){
		Map<String, String> referenceData = new HashMap<String, String>();
		for (Role role : roleDao.getAll()){
			referenceData.put(String.valueOf(role.getRoleName()), role.getRoleName());
		}
		return referenceData;		
	}

}
