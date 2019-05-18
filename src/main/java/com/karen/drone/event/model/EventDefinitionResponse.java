package com.karen.drone.event.model;

import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public class EventDefinitionResponse {

    private UUID eventId;

    public EventDefinitionResponse() {}

    public EventDefinitionResponse(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
