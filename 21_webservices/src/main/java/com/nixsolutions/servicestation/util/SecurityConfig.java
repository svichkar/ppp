package com.nixsolutions.servicestation.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by rybkinrolla on 02.02.2016.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT login AS username, password AS password, 'true' " +
                        "FROM user WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT u.login AS username, r.role_name AS role FROM user u " +
                        "INNER JOIN role r ON u.role_id = r.role_id WHERE u.login = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/homepage").hasAnyRole("MANAGER", "CLIENT")
                .antMatchers("/cars", "/clients", "/orders", "/workers").hasRole("MANAGER")
                .antMatchers("/services/*").permitAll()
                .and().formLogin()
                .loginPage("/")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/workers")
                .and().csrf().disable()
                .exceptionHandling().accessDeniedPage("/?error=1");
    }
}
