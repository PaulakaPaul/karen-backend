package com.karen.drone.comment.model.persistence;

import com.karen.drone.event.model.persistence.EventDAO;
import com.karen.drone.user.models.persistence.UserDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
@Entity
@Table(name = "comments")
public class CommentDAO {

    @Id
    @Column(name = "comment_id")
    private UUID commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventDAO event;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO postedBy;

    @Column(name = "message")
    private String message;

    @Column(name = "posted_at")
    private Date postedAt;

    public CommentDAO() {}

    public CommentDAO(EventDAO event, UserDAO postedBy, String message, Date postedAt) {
        this.event = event;
        this.postedBy = postedBy;
        this.message = message;
        this.postedAt = postedAt;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public EventDAO getEvent() {
        return event;
    }

    public void setEvent(EventDAO event) {
        this.event = event;
    }

    public UserDAO getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(UserDAO postedBy) {
        this.postedBy = postedBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }
}
