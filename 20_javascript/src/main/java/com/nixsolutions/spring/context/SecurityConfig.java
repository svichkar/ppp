package com.nixsolutions.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.sql.DataSource;

/**
 * Created by kozlovskij on 2/3/2016.
 */
@EnableWebSecurity
@Configuration
@ComponentScan(value = "com.nixsolutions")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void  configure (HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                    .antMatchers("/userManagement", "/categoryManagement").access("hasRole('ROLE_ADMIN')")
                    .and()
                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/j_spring_security_check")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/main")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT login AS username, password AS password, 'true' " +
                        "FROM user WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT u.login AS username, " +
                        "CASE r.name WHEN 'admin' THEN 'ROLE_ADMIN' " +
                        "ELSE 'ROLE_USER' END AS role FROM user u " +
                        "INNER JOIN role r ON u.role_id = r.role_id WHERE u.login = ?");
    }
}

