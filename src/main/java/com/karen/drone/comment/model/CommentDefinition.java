package com.karen.drone.comment.model;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class CommentDefinition {

    private String message;

    public CommentDefinition() {}

    public CommentDefinition(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
