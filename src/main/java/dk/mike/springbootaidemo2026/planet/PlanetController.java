package dk.mike.springbootaidemo2026.planet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

/**
 * Controller that provides REST endpoints for managing and retrieving planet data.
 * Planet data is retrieved from a Parquet file using the PlanetParquetService.
 * The endpoints allow clients to view stored planet information.
 */
@RestController
@Slf4j
@RequestMapping("/api/planets")
public class PlanetController {
    private static final int PROCESSING_ITERATIONS = 100;

    private final PlanetParquetService planetParquetService;

    public PlanetController(PlanetParquetService planetParquetService) {
        this.planetParquetService = planetParquetService;
    }

    @GetMapping
    public List<PlanetParquetService.Planet> getPlanets() {
        try {
            return planetParquetService.readStoredPlanets();
        } catch (NoSuchFileException exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Stored planets parquet file not found",
                    exception
            );
        } catch (IOException exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to read stored planets",
                    exception
            );
        }
    }

    @PostMapping
    public int createPlanet(@RequestBody PlanetParquetService.Planet planet) {
        log.info("Saving planet: {}", planet);
        return processPlanetSave();
    }

    private int processPlanetSave() {
        int processedCount = 0;
        for (int iteration = 0; iteration < PROCESSING_ITERATIONS; iteration++) {
            log.debug("Processing iteration {}", iteration);
            processedCount++;
        }
        return processedCount;
    }
}
