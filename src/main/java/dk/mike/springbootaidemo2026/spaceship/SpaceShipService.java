package dk.mike.springbootaidemo2026.spaceship;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-klasse til håndtering af rumskibe.
 * Denne service sørger for at initialisere databasen med standard rumskibe ved opstart.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SpaceShipService {

    private final SpaceShipRepository spaceShipRepository;

    /**
     * Initialiserer databasen med dummy-rumskibe, hvis den er tom.
     * Denne metode køres automatisk efter at Spring-konteksten er oprettet.
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
     * Henter en liste over alle tilgængelige rumskibe i databasen.
     *
     * @return En liste af {@link SpaceShipEntity} objekter.
     */
    public List<SpaceShipEntity> getAllSpaceShips() {
        return spaceShipRepository.findAll();
    }
}
