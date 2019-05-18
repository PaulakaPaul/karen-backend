package com.karen.drone.user.models;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
public class TokenResponse {

    private String access_token;

    public TokenResponse() { }

    public TokenResponse(String token) {
        this.access_token = token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setToken(String token) {
        this.access_token = token;
    }
}
