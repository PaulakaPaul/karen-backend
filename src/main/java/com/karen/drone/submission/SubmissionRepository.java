package com.karen.drone.submission;

import com.karen.drone.submission.model.persistence.SubmissionDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-18
 */
public interface SubmissionRepository extends JpaRepository<SubmissionDAO, UUID> {
}
