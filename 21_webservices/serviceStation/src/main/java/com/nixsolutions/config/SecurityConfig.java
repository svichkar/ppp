package com.nixsolutions.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.nixsolutions.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/resources/style/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/logout").permitAll()
				.antMatchers("/navigation").permitAll()
				.antMatchers("/customer/**").hasAuthority("ROLE_CUSTOMER")
				.antMatchers("/admin/**").hasAuthority("ROLE_MANAGER")
				 .anyRequest().authenticated()
				 	.and()
				 	.exceptionHandling().accessDeniedPage("/403")
				/*.antMatchers("/views/adminPage.jsp").access("hasRole('ROLE_CUSTOMER')")
					.antMatchers("/navigation").access("hasRole('manager') or hasRole('customer')")
					.antMatchers("/adminPage.jsp", "/customerPage.jsp", "/carPage.jsp", "/partPage.jsp").access("hasRole('manager')")*/
					.and()
				.formLogin()
					.loginPage("/")
					.loginProcessingUrl("/j_spring_security_check")
					.defaultSuccessUrl("/navigation")
					.usernameParameter("j_username").passwordParameter("j_password")
					.and()
				.logout()
					.permitAll()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true);

	}

	@Bean
	public ShaPasswordEncoder getShaPasswordEncoder() {
		return new ShaPasswordEncoder();
	}

}