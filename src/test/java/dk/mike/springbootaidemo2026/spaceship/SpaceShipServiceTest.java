package dk.mike.springbootaidemo2026.spaceship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpaceShipServiceTest {

    @Autowired
    private SpaceShipService spaceShipService;

    @Autowired
    private SpaceShipRepository spaceShipRepository;

    @Test
    void testDummySpaceShipsAreCreatedOnStartup() {
        // Since init() is @PostConstruct and @SpringBootTest starts the context, 
        // the spaceships should already be in the database.

        List<SpaceShipEntity> ships = spaceShipService.getAllSpaceShips();

        assertThat(ships).isNotEmpty();
        assertThat(ships).extracting(SpaceShipEntity::getName)
                .containsExactlyInAnyOrder("Voyager", "Enterprise-D", "Millennium Falcon");
    }
}
