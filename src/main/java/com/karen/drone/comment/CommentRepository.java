package com.karen.drone.comment;

import com.karen.drone.comment.model.persistence.CommentDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public interface CommentRepository extends JpaRepository<CommentDAO, UUID> {
}
