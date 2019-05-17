package com.karen.drone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    public void configure(HttpSecurity http){
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
