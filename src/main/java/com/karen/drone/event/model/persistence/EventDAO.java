package com.karen.drone.event.model.persistence;

import com.karen.drone.comment.model.persistence.CommentDAO;
import com.karen.drone.event.model.components.EventStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
@Entity
@Table(name = "events")
public class EventDAO {

    @Id
    @Column(name = "event_id")
    private UUID eventId;

    @Column(name = "drone_id")
    private String droneId;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "longitude")
    private long longitude;

    @Column(name = "latitude")
    private long latitude;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "saved_at")
    private Date reportedAt;

    @OneToMany(mappedBy = "event", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("posted_at desc")
    private List<CommentDAO> comments;

    public EventDAO() {
    }

    public EventDAO(UUID eventId, String droneId, byte[] image, long longitude, long latitude, EventStatus status, Date reportedAt, List<CommentDAO> comments) {
        this.eventId = eventId;
        this.droneId = droneId;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
        this.reportedAt = reportedAt;
        this.comments = comments;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Date getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Date reportedAt) {
        this.reportedAt = reportedAt;
    }

    public List<CommentDAO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDAO> comments) {
        this.comments = comments;
    }
}
