package com.karen.drone.security;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 01/04/2019
 */
public class JwtAuthenticationException extends RuntimeException {

    public JwtAuthenticationException(String message) {
        super(message);
    }

}

