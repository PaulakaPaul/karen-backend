package com.karen.drone.event;

import com.karen.drone.event.model.persistence.EventDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public interface EventRepository extends JpaRepository<EventDAO, UUID> {

}
