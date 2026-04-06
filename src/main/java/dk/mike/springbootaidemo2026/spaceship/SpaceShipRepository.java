package dk.mike.springbootaidemo2026.spaceship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for handling {@link SpaceShipEntity} in the database.
 */
@Repository
public interface SpaceShipRepository extends JpaRepository<SpaceShipEntity, Long> {
}
