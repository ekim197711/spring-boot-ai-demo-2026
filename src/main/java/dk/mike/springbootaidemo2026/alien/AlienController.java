package dk.mike.springbootaidemo2026.alien;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing aliens.
 */
@RestController
@RequestMapping("/api/aliens")
@RequiredArgsConstructor
public class AlienController {

    private final AlienService alienService;

    @GetMapping
    public List<AlienDto> getAllAliens() {
        return alienService.getAllAliens();
    }

    @PostMapping
    public AlienDto createAlien(@RequestBody AlienDto alienDto) {
        return alienService.createAlien(alienDto);
    }
}
