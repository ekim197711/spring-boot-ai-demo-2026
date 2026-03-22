package dk.mike.springbootaidemo2026.openai.embedding;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MikesOpenAiEmbeddingServiceTest {

    @Autowired
    private MikesOpenAiEmbeddingService mikesOpenAiEmbeddingService;

    @Test
    void resultFromEmbedding() {
        String result = mikesOpenAiEmbeddingService
                .recommendNextSong("I'm happy from despicable me");
        System.out.println(result);
    }

    @Test
    void resultFromEmbeddingFurElise() {
        String result = mikesOpenAiEmbeddingService
                .recommendNextSong("Fur Elise by Beethoven");
        System.out.println(result);
    }

    @Test
    void resultWeAreTheChampionsByQueen() {
        String result = mikesOpenAiEmbeddingService
                .recommendNextSong("Sound of silence");
        System.out.println(result);
    }
}