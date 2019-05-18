package com.karen.drone.comment.model;

import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class CommentDefinitionResponse {

    private UUID commentId;

    public CommentDefinitionResponse() {
    }

    public CommentDefinitionResponse(UUID commentId) {
        this.commentId = commentId;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }
}
