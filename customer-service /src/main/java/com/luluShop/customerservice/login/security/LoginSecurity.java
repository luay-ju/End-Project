package com.luluShop.customerservice.login.security;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
*/

//@Configuration
public class LoginSecurity {
/*

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                );

        return http.build();
    }


*/
}
