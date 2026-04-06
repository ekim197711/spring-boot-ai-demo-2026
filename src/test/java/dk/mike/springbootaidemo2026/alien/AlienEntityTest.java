package dk.mike.springbootaidemo2026.alien;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-klasse til verifikation af AlienEntity-funktionalitet.
 */
public class AlienEntityTest {

    /**
     * Verificerer at den tilpassede toString-metode i AlienEntity indeholder de forventede felter.
     */
    @Test
    public void testToString() {
        AlienEntity entity = new AlienEntity(1L, "Martian", true, 0, 100);
        String toStringResult = entity.toString();

        assertThat(toStringResult).contains("kind=Martian");
        assertThat(toStringResult).contains("powerLevel=100");
        assertThat(toStringResult).doesNotContain("isHumanoid");
        assertThat(toStringResult).doesNotContain("numberOfTentacles");
    }
}
