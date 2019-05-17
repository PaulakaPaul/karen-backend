package com.karen.drone.user;

import com.karen.drone.user.models.persistence.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Daniel Incicau, daniel.incicau@busymachines.com
 * @since 2019-05-17
 */
@Repository
public interface UserRepository extends JpaRepository<UserDAO, UUID> {
    Optional<UserDAO> findByEmail(String email);
}
