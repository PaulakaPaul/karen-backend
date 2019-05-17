package com.karen.drone.user.models;

import io.swagger.annotations.ApiModel;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
@ApiModel(description = "User definition model")
public class UserDefinition {

    private String email;
    private String password;
    private String name;

    public UserDefinition() {
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;
    }

}
