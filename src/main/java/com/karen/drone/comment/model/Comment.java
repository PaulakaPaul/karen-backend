package com.karen.drone.comment.model;

import com.karen.drone.user.models.UserProfile;

import java.util.Date;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class Comment {

    private UUID commentId;
    private String message;
    private UserProfile postedBy;
    private Date postedAt;

    public Comment() {}

    public Comment(UUID commentId, String message, UserProfile postedBy, Date postedAt) {
        this.commentId = commentId;
        this.message = message;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserProfile getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(UserProfile postedBy) {
        this.postedBy = postedBy;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }
}
