package dk.mike.springbootaidemo2026.alien;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for handling aliens and creating dummy data.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@ToString
public class AlienService {

    private final AlienRepository alienRepository;

    @PostConstruct
    public void init() {
        if (alienRepository.count() == 0) {
            log.info("Creating dummy aliens...");
            alienRepository.save(new AlienEntity(null, "Martian", true, 0, 100));
            alienRepository.save(new AlienEntity(null, "Octopoid", false, 8, 250));
            alienRepository.save(new AlienEntity(null, "Xenomorph", false, 0, 500));
            log.info("Dummy aliens created.");
        }
    }

    public List<AlienDto> getAllAliens() {
        return alienRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AlienDto createAlien(AlienDto alienDto) {
        AlienEntity entity = convertToEntity(alienDto);
        AlienEntity savedEntity = alienRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private AlienDto convertToDto(AlienEntity entity) {
        return new AlienDto(
                entity.getId(),
                entity.getKind(),
                entity.isHumanoid(),
                entity.getNumberOfTentacles(),
                entity.getPowerLevel()
        );
    }

    private AlienEntity convertToEntity(AlienDto dto) {
        return new AlienEntity(
                dto.id(),
                dto.kind(),
                dto.isHumanoid(),
                dto.numberOfTentacles(),
                dto.powerLevel()
        );
    }
}
