package com.karen.drone.event.model.components;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class Coords {

    private long longitude;
    private long latitude;

    public Coords() {
    }

    public Coords(long longitude, long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}
