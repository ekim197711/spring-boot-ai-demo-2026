package dk.mike.springbootaidemo2026.alien;

/**
 * Data Transfer Object (DTO) for aliens.
 */
public record AlienDto(Long id, String kind, boolean isHumanoid, int numberOfTentacles, int powerLevel) {
    /**
     * Returns a string representation of the alien based on kind and power level.
     *
     * @return String representation of AlienDto.
     */
    @Override
    public String toString() {
        return "AlienDto[kind=" + kind + ", powerLevel=" + powerLevel + "]";
    }
	
}
