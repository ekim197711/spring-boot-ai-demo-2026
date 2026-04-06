# package dk.mike.springbootaidemo2026.spaceship;
#
# public record SpaceShipDto(Long id, String name, Integer crewCount, String destination) {
# }

# package dk.mike.springbootaidemo2026.spaceship

# Diese Klasse stellt ein Raumschiff dar.
class SpaceShipDto:
    def __init__(self, id: int, name: str, crewCount: int, destination: str):
        self.id = id
        self.name = name
        self.crewCount = crewCount
        self.destination = destination
