package com.karen.drone.user.models;

import io.swagger.annotations.ApiModel;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
@ApiModel(description = "User login model")
public class UserLogin {

    public String email;
    public String password;

    public UserLogin() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
