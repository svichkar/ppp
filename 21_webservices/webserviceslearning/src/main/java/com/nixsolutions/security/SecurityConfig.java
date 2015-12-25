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
public class SecurityConfig 
extends WebSecurityConfigurerAdapter 
{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("KennySmiles").password("password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("JohnHughes").password("password").roles("USER");
        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT * FROM sqllab.user WHERE user_login = ?").authoritiesByUsernameQuery("SELECT * FROM sqllab.role WHERE role_id = ?");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/userPage.jsp", "/login.do", "/nav.do").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/admin/*", "/services/*").access("hasRole('ROLE_ADMIN')")
				.and()
			.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/j_spring_security_check")
				.usernameParameter("login")
				.passwordParameter("password")
				.defaultSuccessUrl("/login.do")
				.and()
			.csrf().disable()
				//.and()
			.exceptionHandling().accessDeniedPage("/");
	}
}
