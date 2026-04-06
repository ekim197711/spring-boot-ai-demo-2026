package dk.mike.springbootaidemo2026.alien;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AlienServiceTest {

    @Autowired
    private AlienService alienService;

    @Test
    void testToString() {
        String result = alienService.toString();
        assertThat(result).contains("AlienService");
        assertThat(result).contains("alienRepository");
    }
}
