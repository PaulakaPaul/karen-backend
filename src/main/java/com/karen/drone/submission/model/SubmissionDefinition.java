package com.karen.drone.submission.model;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class SubmissionDefinition {

    private String message;
    private String image;

    public SubmissionDefinition() {}

    public SubmissionDefinition(String message, String image) {
        this.message = message;
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
