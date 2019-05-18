package com.karen.drone.comment.model;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class CommentDefinitionResponse {

    private Integer commentId;

    public CommentDefinitionResponse() {
    }

    public CommentDefinitionResponse(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}
