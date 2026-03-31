package dk.mike.springbootaidemo2026.spaceship;

/**
 * Data Transfer Object (DTO) for et rumskib.
 *
 * @param id          Unik identifikator for rumskibet.
 * @param name        Navnet på rumskibet.
 * @param crewCount   Antal besætningsmedlemmer ombord.
 * @param destination Rumskibets destination.
 */
public record SpaceShipDto(Long id, String name, Integer crewCount, String destination) {
}
