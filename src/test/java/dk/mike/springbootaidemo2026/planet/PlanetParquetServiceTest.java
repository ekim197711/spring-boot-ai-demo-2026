package dk.mike.springbootaidemo2026.planet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlanetParquetServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void savesAndReadsDummyPlanets() throws IOException {
        PlanetParquetService service = new PlanetParquetService();
//        Path parquetFile = tempDir.resolve("planets.parquet");
        Path parquetFile = Path.of("./myPlanets.parquet");

        Path savedFile = service.saveDummyPlanets(parquetFile);
        List<PlanetParquetService.Planet> planets = service.readPlanets(savedFile);

        assertEquals(parquetFile, savedFile);
        assertTrue(Files.exists(savedFile));
        assertTrue(Files.size(savedFile) > 0);
        assertEquals(10, planets.size());
        assertEquals(new PlanetParquetService.Planet("Mercury", "Solar System", 1, 0, false), planets.get(0));
        assertEquals(new PlanetParquetService.Planet("Earth", "Solar System", 3, 1, true), planets.get(2));
        assertEquals(new PlanetParquetService.Planet("Proxima Centauri b", "Proxima Centauri", 1, 0, true), planets.get(9));
    }
}
