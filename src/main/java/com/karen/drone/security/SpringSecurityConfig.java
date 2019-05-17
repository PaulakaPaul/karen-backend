package com.karen.drone.security;

import com.karen.drone.DroneApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public static String ROLE_USER = "USER";
    public static String ROLE_ADMIN = "ADMIN";

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**") .permitAll()
                .antMatchers("/user/authenticated").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}
