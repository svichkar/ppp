package com.nixsolutions.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.nixsolutions.error.CustomException;

@EnableWebSecurity
@Configuration
@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("admin").password("1").roles("ADMIN");
		// auth.inMemoryAuthentication().withUser("user").password("1").roles("USER");
		// auth.inMemoryAuthentication().withUser("dinio").password("1").roles("USER");

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, 'true' " + "from sqllab.user " + "where username=?")
				.authoritiesByUsernameQuery("select username, " + "case rolename " + "when 'admin' then 'ROLE_ADMIN' "
						+ "else 'ROLE_USER' end as autority "
						+ "from sqllab.user inner join sqllab.role on user.role_id=role.role_id where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/navigation", "/car*", "/customer*", "/order*", "/part*", "/user*", "/worker*")
				.access("hasRole('ROLE_ADMIN')").anyRequest().permitAll()
				.antMatchers("/userpage.jsp", "/logout", "/login")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/").failureUrl("/error").loginProcessingUrl("/j_spring_security_check").usernameParameter("login")
				.passwordParameter("password").defaultSuccessUrl("/login").and().csrf().and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/").and().anonymous().and().authorizeRequests()
				.antMatchers("/rest/**").permitAll().and().csrf().disable().exceptionHandling().accessDeniedPage("/error");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/style/*");
	}

}
