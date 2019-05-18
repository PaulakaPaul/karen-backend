package com.karen.drone.event.model;

import com.karen.drone.comment.model.Comment;
import com.karen.drone.event.model.components.Coords;
import com.karen.drone.event.model.components.EventStatus;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class Event {

    private UUID eventId;
    private String droneId;
    private String image;
    private Coords coords;
    private EventStatus status;
    private List<Comment> comments;
    private Date reportedAt;

    public Event() { }

    public Event(UUID eventId, String droneId, String image, Coords coords, EventStatus status, List<Comment> comments, Date reportedAt) {
        this.eventId = eventId;
        this.droneId = droneId;
        this.image = image;
        this.coords = coords;
        this.status = status;
        this.comments = comments;
        this.reportedAt = reportedAt;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Date reportedAt) {
        this.reportedAt = reportedAt;
    }
}
