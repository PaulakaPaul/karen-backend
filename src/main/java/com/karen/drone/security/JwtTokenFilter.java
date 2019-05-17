package com.karen.drone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
@Component
public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtUtil.resolveToken((HttpServletRequest) req);
        if (token != null && jwtUtil.validateToken(token)) {
            AuthCtx auth = jwtUtil.parseToken(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(req, res);
    }
}
