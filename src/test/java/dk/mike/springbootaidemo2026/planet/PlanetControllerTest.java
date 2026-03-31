package dk.mike.springbootaidemo2026.planet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PlanetControllerTest {

    private final PlanetParquetService planetParquetService = new PlanetParquetService();
    private final PlanetController planetController = new PlanetController(planetParquetService);

    /**
     * Test case: Successfully retrieve the list of planets.
     * Expectation: Returns a non-empty list of planets.
     */
    @Test
    public void testGetPlanetsSuccessfully() throws IOException {
        // Arrange: Create a temporary Parquet file with dummy planet data
        Path tempPath = Files.createTempFile("test-planets", ".parquet");
        planetParquetService.saveDummyPlanets(tempPath);
        System.setProperty("planet.parquet.path", tempPath.toString());

        // Act
        List<PlanetParquetService.Planet> planets = planetController.getPlanets();

        // Assert
        assertThat(planets).isNotEmpty();
        assertThat(planets.get(0).name()).isEqualTo("Mercury");
        assertThat(planets.size()).isEqualTo(10);

        // Clean up
        Files.deleteIfExists(tempPath);
    } @Test
    public void testGetPlanetsSuccessfully() throws IOException {
        // Arrange: Create a temporary Parquet file with dummy planet data
        Path tempPath = Files.createTempFile("test-planets", ".parquet");
        planetParquetService.saveDummyPlanets(tempPath);
        System.setProperty("planet.parquet.path", tempPath.toString());

        // Act
        List<PlanetParquetService.Planet> planets = planetController.getPlanets();

        // Assert
        assertThat(planets).isNotEmpty();
        assertThat(planets.get(0).name()).isEqualTo("Mercury");
        assertThat(planets.size()).isEqualTo(10);

        // Clean up
        Files.deleteIfExists(tempPath);
    }

    /**
     * Test case: Parquet file not found while retrieving planets.
     * Expectation: Throws a ResponseStatusException with HTTP 404 status and the expected error message.
     */
    @Test
    public void testGetPlanetsFileNotFound() {
        // Arrange: Point to a non-existent file
        System.setProperty("planet.parquet.path", "./nonexistentFile.parquet");

        // Act and Assert
        assertThatThrownBy(() -> planetController.getPlanets())
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Stored planets parquet file not found")
                .extracting("status").isEqualTo(HttpStatus.NOT_FOUND);
    }

    /**
     * Test case: IOException occurs while retrieving planets.
     * Expectation: Throws a ResponseStatusException with HTTP 500 status and the expected error message.
     */
    @Test
    public void testGetPlanetsIOException() throws IOException {
        // Arrange: Use a directory instead of a valid Parquet file to cause an IOException
        Path dirPath = Files.createTempDirectory("invalid-planets");
        System.setProperty("planet.parquet.path", dirPath.toString());

        // Act and Assert
        assertThatThrownBy(() -> planetController.getPlanets())
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Failed to read stored planets")
                .extracting("status").isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        // Clean up
        Files.deleteIfExists(dirPath);
    }
}