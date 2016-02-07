package com.nixsolutions.servicestation.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(value = "com.nixsolutions")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT login AS username, password AS password, 'true' " +
                        "FROM user WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT u.login AS username, " +
                        "CASE r.role_name WHEN 'manager' THEN 'ROLE_ADMIN' " +
                        "ELSE 'ROLE_USER' END AS role FROM user u " +
                        "INNER JOIN role r ON u.role_id = r.role_id WHERE u.login = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/homepage").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/cars", "/clients", "/orders", "/workers").access("hasRole('ROLE_ADMIN')")
                .and().formLogin()
                .loginPage("/")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/homepage")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/homepage");
    }
}
