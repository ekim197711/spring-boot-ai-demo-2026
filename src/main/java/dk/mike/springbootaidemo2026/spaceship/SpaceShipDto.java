package dk.mike.springbootaidemo2026.spaceship;

/**
 * Data Transfer Object (DTO) for a spaceship.
 *
 * @param id          Unique identifier for the spaceship.
 * @param name        Name of the spaceship.
 * @param crewCount   Number of crew members on board.
 * @param destination The spaceship's destination.
 */
public record SpaceShipDto(Long id, String name, Integer crewCount, String destination) {
}
