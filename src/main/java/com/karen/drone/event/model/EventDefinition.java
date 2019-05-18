package com.karen.drone.event.model;

import com.karen.drone.event.model.components.Coords;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class EventDefinition {

    private String droneId;
    private String image;
    private Coords coords;

    public EventDefinition() { }

    public EventDefinition(String droneId, String image, Coords coords) {
        this.droneId = droneId;
        this.image = image;
        this.coords = coords;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

}

