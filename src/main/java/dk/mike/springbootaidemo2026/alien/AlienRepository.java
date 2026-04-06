package dk.mike.springbootaidemo2026.alien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for AlienEntity entities.
 */
@Repository
public interface AlienRepository extends JpaRepository<AlienEntity, Long> {
}
