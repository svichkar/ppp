package com.nixsolutions.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT user_name, password, 'true' FROM user WHERE user_name = ?")
				.authoritiesByUsernameQuery("SELECT u.user_name AS username," + "CASE r.role_name "
						+ "WHEN 'Administrator' THEN 'ROLE_ADMIN'" + "WHEN 'Manager' THEN  'ROLE_MANAGER'"
						+ "WHEN 'Teacher' THEN 'ROLE_TEACHER'" + "WHEN 'Student' THEN 'ROLE_STUDENT'"
						+ "END AS role FROM user u "
						+ "INNER JOIN role r ON u.role_id = r.role_id WHERE u.user_name = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/css/**", "/").permitAll()
			.antMatchers("/css/**", "/students/*", "/studentGroups/*", "/subjects/*", "/journal.do").access("hasRole('ROLE_MANAGER')")
			.antMatchers("/AdminHome.do", "/addNewUser.do", "/editUser.do").access("hasRole('ROLE_ADMIN')")
			.and()
		.formLogin()
			.loginPage("/")
			.loginProcessingUrl("/j_spring_security_check")
			.usernameParameter("login")
			.passwordParameter("password")
			.defaultSuccessUrl("/login.do")
			.and()
		.csrf()
			.and()
		.exceptionHandling().accessDeniedPage("/Access_Denied");
	}
}
