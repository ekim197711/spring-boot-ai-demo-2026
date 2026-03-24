package dk.mike.springbootaidemo2026.openai.jedis;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MikesOpenAiEmbeddingServiceWithVectorStoreTest {
    @Autowired
    private MikesOpenAiEmbeddingServiceWithVectorStore mikesOpenAiEmbeddingServiceWithVectorStore;

    @Test
    void recommendNextSong() {
        List<Document> documents = mikesOpenAiEmbeddingServiceWithVectorStore
                .recommendNextSong("I'm singing in the rain - A song about falling in love");
        System.out.println(documents);
    }
}