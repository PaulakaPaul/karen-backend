package com.karen.drone.submission.model;

import com.karen.drone.submission.model.components.SubmissionStatus;
import com.karen.drone.user.models.UserProfile;

import java.util.Date;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class Submission {

    private UUID submissionId;
    private String message;
    private String image;
    private UserProfile submittedBy;
    private Date submittedAt;
    private SubmissionStatus status;

    public Submission() {
    }

    public Submission(UUID submissionId, String message, String image, UserProfile submittedBy, Date submittedAt, SubmissionStatus status) {
        this.submissionId = submissionId;
        this.message = message;
        this.image = image;
        this.submittedBy = submittedBy;
        this.submittedAt = submittedAt;
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserProfile getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(UserProfile submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }
}
