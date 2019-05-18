package com.karen.drone.security;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 01/04/2019
 */
class JwtAuthenticationException extends Exception {

    JwtAuthenticationException(String message) {
        super(message);
    }

}

