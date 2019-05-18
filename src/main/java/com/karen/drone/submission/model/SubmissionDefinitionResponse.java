package com.karen.drone.submission.model;

import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class SubmissionDefinitionResponse {

    private UUID submissionId;

    public SubmissionDefinitionResponse() {}

    public SubmissionDefinitionResponse(UUID submissionId) {
        this.submissionId = submissionId;
    }

    public UUID getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(UUID submissionId) {
        this.submissionId = submissionId;
    }
}
