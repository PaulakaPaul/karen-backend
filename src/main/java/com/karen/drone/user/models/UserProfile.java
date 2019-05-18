package com.karen.drone.user.models;

import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class UserProfile {

    private UUID userId;
    private String name;

    public UserProfile() {}

    public UserProfile(UUID userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
