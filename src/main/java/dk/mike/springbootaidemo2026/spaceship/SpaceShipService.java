package dk.mike.springbootaidemo2026.spaceship;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling spaceships.
 * This service ensures the database is initialized with default spaceships at startup.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SpaceShipService {

    private final SpaceShipRepository spaceShipRepository;

    /**
     * Initializes the database with dummy spaceships if it is empty.
     * This method runs automatically after the Spring context is created.
     */
    @PostConstruct
    public void init() {
        log.info("Initializing dummy spaceships...");
        if (spaceShipRepository.count() == 0) {
            SpaceShipEntity voyager = new SpaceShipEntity(null, "Voyager", 150, "Delta Quadrant");
            SpaceShipEntity enterprise = new SpaceShipEntity(null, "Enterprise-D", 1012, "Deep Space");
            SpaceShipEntity millenniumFalcon = new SpaceShipEntity(null, "Millennium Falcon", 4, "Tatooine");

            spaceShipRepository.saveAll(List.of(voyager, enterprise, millenniumFalcon));
            log.info("Saved dummy spaceships: Voyager, Enterprise-D, Millennium Falcon");
        }
    }

    /**
     * Retrieves a list of all available spaceships in the database.
     *
     * @return A list of {@link SpaceShipEntity} objects.
     */
    public List<SpaceShipEntity> getAllSpaceShips() {
        return spaceShipRepository.findAll();
    }
}
