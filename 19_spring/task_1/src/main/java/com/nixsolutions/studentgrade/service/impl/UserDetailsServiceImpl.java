package com.nixsolutions.studentgrade.service.impl;


import com.nixsolutions.studentgrade.model.Role;
import com.nixsolutions.studentgrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by konstantin on 1/31/2016.
 */
@Service
@Transactional(readOnly=true)
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        com.nixsolutions.studentgrade.model.User user = userService.findByLogin(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.nixsolutions.studentgrade.model.User user, List<GrantedAuthority> authorities) {
        return new User(user.getLogin(), user.getUserPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role userRole) {

        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        return auth;
    }

}
