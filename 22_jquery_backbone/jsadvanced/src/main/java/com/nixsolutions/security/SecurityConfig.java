package com.nixsolutions.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Configuration
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("KennySmiles").password("password").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("JohnHughes").password("password").roles("USER");
        auth
        	.jdbcAuthentication()
        	.dataSource(dataSource)
        	.usersByUsernameQuery("SELECT user_login AS username, user_password AS password, 'true' " +
        			"FROM sqllab.user WHERE user_login = ?")
        	.authoritiesByUsernameQuery("SELECT u.user_login AS username, " +
        			"CASE r.role_name WHEN 'Administrator' THEN 'ROLE_ADMIN' " +
        			"ELSE 'ROLE_USER' END AS role FROM sqllab.user u " + 
        			"INNER JOIN sqllab.role r ON u.role_id = r.role_id WHERE u.user_login = ?");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/userPage.jsp", "/login.do", "/nav.do").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
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
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.and()
			.exceptionHandling().accessDeniedPage("/");
	}
}
