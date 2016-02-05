package com.nixsolutions.asp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nixsolutions.asp.dao.UserDao;
import com.nixsolutions.asp.entity.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User myUser = userDao.getByUserName(userName);
		if (myUser == null)
			throw new UsernameNotFoundException("Username: '" + userName + "' not found in database");
		String role = myUser.getRole().getRoleName();
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		if (role.toLowerCase().equals("administrator")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			if (role.toLowerCase().equals("manager")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
			} else {
				if (role.toLowerCase().equals("teacher")) {
					authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
				} else {
					if (role.toLowerCase().equals("student")) {
						authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
					}
				}
			}
		}
		UserDetails user = new org.springframework.security.core.userdetails.User(myUser.getUserName(), myUser.getPassword(), authorities);
		return user;
	}
}
