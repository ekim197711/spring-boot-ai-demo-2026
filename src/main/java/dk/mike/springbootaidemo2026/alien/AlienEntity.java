package dk.mike.springbootaidemo2026.alien;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing an alien.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlienEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kind;

    private boolean isHumanoid;

    private int numberOfTentacles;

    private int powerLevel;

    /**
     * Returns a string representation of the entity based on kind and power level.
     *
     * @return String representation of AlienEntity.
     */
    @Override
    public String toString() {
        return "AlienEntity[kind=" + kind + ", powerLevel=" + powerLevel + "]";
    }
}
