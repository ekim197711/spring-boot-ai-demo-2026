package dk.mike.springbootaidemo2026.spaceship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface til håndtering af {@link SpaceShipEntity} i databasen.
 */
@Repository
public interface SpaceShipRepository extends JpaRepository<SpaceShipEntity, Long> {
}
