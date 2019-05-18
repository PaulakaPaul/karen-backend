package com.karen.drone.submission.model.persistence;

import com.karen.drone.event.model.persistence.EventDAO;
import com.karen.drone.submission.model.components.SubmissionStatus;
import com.karen.drone.user.models.persistence.UserDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
@Entity
@Table(name = "submissions")
public class SubmissionDAO {

    @Id
    @Column(name = "submission_id")
    private UUID submissionId;

    @Column(name = "message")
    private String message;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "status")
    private SubmissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventDAO event;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserDAO submittedBy;

    @Column(name = "submitted_at")
    private Date submittedAt;

    public SubmissionDAO() {
    }

    public SubmissionDAO(UUID submissionId, String message, String imageType, byte[] image, SubmissionStatus status, EventDAO event, UserDAO submittedBy, Date submittedAt) {
        this.submissionId = submissionId;
        this.message = message;
        this.imageType = imageType;
        this.image = image;
        this.status = status;
        this.event = event;
        this.submittedBy = submittedBy;
        this.submittedAt = submittedAt;
    }

    public UUID getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(UUID submissionId) {
        this.submissionId = submissionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }

    public EventDAO getEvent() {
        return event;
    }

    public void setEvent(EventDAO event) {
        this.event = event;
    }

    public UserDAO getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(UserDAO submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }
}
