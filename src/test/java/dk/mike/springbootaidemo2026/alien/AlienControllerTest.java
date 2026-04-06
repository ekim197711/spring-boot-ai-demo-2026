package dk.mike.springbootaidemo2026.alien;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testklasse for AlienController.
 * Denne klasse bruger @SpringBootTest til at indlæse den fulde applikationskontekst og verificere adfærden
 * af REST-endpoints til styring af rumvæsener.
 */
@SpringBootTest
public class AlienControllerTest {

    @Autowired
    private AlienController alienController;

    /**
     * Verificerer at alle rumvæsener hentes korrekt fra controlleren.
     * Da dummy-rumvæsener oprettes ved opstart, forventer vi mindst 3 rumvæsener.
     */
    @Test
    public void testGetAllAliens() {
        // Since dummy aliens are created on startup, we expect at least 3 aliens.
        List<AlienDto> availableAliens = alienController.getAllAliens();

        assertThat(availableAliens).hasSizeGreaterThanOrEqualTo(3);
        assertThat(availableAliens.get(0).kind()).isEqualTo("Martian");
    }

    /**
     * Verificerer oprettelsen af et nyt rumvæsen via controlleren.
     * Sikrer at det oprettede rumvæsen har de korrekte egenskaber og et genereret ID.
     */
    @Test
    public void testCreateAlien() {
        AlienDto newAlien = new AlienDto(null, "Grey", true, 0, 150);
        AlienDto createdAlien = alienController.createAlien(newAlien);

        assertThat(createdAlien.kind()).isEqualTo("Grey");
        assertThat(createdAlien.powerLevel()).isEqualTo(150);
        assertThat(createdAlien.id()).isNotNull();
    }
}
