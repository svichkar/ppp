package com.nixsolutions.studentgrade.service.impl;

//import com.nixsolutions.studentgrade.model.User;

import com.nixsolutions.studentgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by konstantin on 1/31/2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        com.nixsolutions.studentgrade.model.User userInfo = userService.findByLogin(username);

        GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole().getRoleName());
        UserDetails userDetails = (UserDetails) new User(userInfo.getLogin(),
                userInfo.getUserPassword(), Arrays.asList(authority));
        return userDetails;
    }


}
