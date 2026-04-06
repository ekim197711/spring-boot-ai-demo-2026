package dk.mike.springbootaidemo2026.spaceship;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a spaceship in the database.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceShipEntity {

    /**
     * Unique ID for the spaceship.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the spaceship.
     */
    private String name;

    /**
     * Number of crew members.
     */
    private Integer crewCount;

    /**
     * The spaceship's destination.
     */
    private String destination;

}
