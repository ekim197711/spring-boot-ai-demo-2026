package dk.mike.springbootaidemo2026.alien;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-klasse til verifikation af AlienDto-funktionalitet.
 */
public class AlienDtoTest {

    /**
     * Verificerer at den tilpassede toString-metode i AlienDto indeholder de forventede felter.
     */
    @Test
    public void testToString() {
        AlienDto dto = new AlienDto(1L, "Martian", true, 0, 100);
        String toStringResult = dto.toString();

        assertThat(toStringResult).contains("kind=Martian");
        assertThat(toStringResult).contains("powerLevel=100");
        assertThat(toStringResult).doesNotContain("isHumanoid");
        assertThat(toStringResult).doesNotContain("numberOfTentacles");
    }
}
