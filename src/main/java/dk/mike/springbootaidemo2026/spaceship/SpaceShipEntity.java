package dk.mike.springbootaidemo2026.spaceship;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entitetsklasse der repræsenterer et rumskib i databasen.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceShipEntity {

    /**
     * Unik ID for rumskibet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Navnet på rumskibet.
     */
    private String name;

    /**
     * Antal besætningsmedlemmer.
     */
    private Integer crewCount;

    /**
     * Rumskibets destination.
     */
    private String destination;

}
