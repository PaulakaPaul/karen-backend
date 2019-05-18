package com.karen.drone.util;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class Image {
    private byte[] bytes;
    private String type;

    public Image(byte[] bytes, String type) {
        this.bytes = bytes;
        this.type = type;
    }

    public byte[] getBytes() {
        return bytes;
    }


    public String getType() {
        return type;
    }

}
