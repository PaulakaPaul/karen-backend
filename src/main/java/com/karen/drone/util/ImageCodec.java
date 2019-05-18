package com.karen.drone.util;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class ImageCodec {

    public static Image decodeImage(String image) {
        String mimeType = extractMimeType(image);
        if(mimeType.equals("")) {
            return null;
        }
        byte[] decodedImg = extractImage(image);
        return new Image(decodedImg, mimeType);
    }

    public static String encodeImage(String imgType, byte[] image) {
        if(image != null) {
            String encodedImg = Base64.getEncoder().encodeToString(image);
            return "data:" + imgType + ";base64," + encodedImg;
        }
        else return null;
    }

    private static String extractMimeType(final String encoded) {
        final Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
        final Matcher matcher = mime.matcher(encoded);
        if (!matcher.find())
            return "";
        return matcher.group(1).toLowerCase();
    }

    private static byte[] extractImage(final String encoded) {
        final Pattern mime = Pattern.compile("^data:[a-zA-Z0-9]+/[a-zA-Z0-9]+.*,.([a-zA-Z0-9/]+)");
        final Matcher matcher = mime.matcher(encoded);
        if (!matcher.find())
            return new byte[0];
        return Base64.getDecoder().decode(matcher.group(1));
    }
}
