package com.karen.drone.submission.model;

import com.karen.drone.submission.model.components.SubmissionStatus;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class ChangeStatus {

    private SubmissionStatus status;

    public ChangeStatus() {}

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }
}
