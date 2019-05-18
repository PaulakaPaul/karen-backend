package com.karen.drone.util;

import com.karen.drone.event.model.components.Coords;

import java.util.Random;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class GeoUtil {

    public static Coords getRandomLocationInRadius(Coords center, int radius) {
        double x0 = center.getLatitude();
        double y0 = center.getLongitude();

        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(y0);

        double foundLatitude = new_x + x0;
        double foundLongitude = y + y0;
        Coords randomLocation = new Coords(foundLongitude, foundLatitude);
        return randomLocation;
    }

}
