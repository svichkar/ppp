package com.nixsolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.entity.User;
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		//User user = userService.getUserByName(arg0);
		return null;
	}

}
