package com.nixsolutions.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
//@Configuration
//@ComponentScan
public class SecurityConfig 
extends WebSecurityConfigurerAdapter 
{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("KennySmiles").password("password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("JohnHughes").password("password").roles("USER");
        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT * FROM sqllab.user WHERE user_login = ?").authoritiesByUsernameQuery("SELECT * FROM sqllab.role WHERE role_id = ?");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login.html")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/nav.do")
				.access("hasRole('ROLE_USER')")
				.and()
			.authorizeRequests()
				.antMatchers("/userPage*")
				.access("hasRole('ROLE_USER')")
				.and()
			.authorizeRequests()
				.antMatchers("/order*")
				.access("hasRole('ROLE_ADMIN')")
				.and()
			
			.logout()
				.permitAll();
	}
}
