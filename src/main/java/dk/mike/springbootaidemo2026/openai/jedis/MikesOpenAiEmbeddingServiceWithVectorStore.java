package dk.mike.springbootaidemo2026.openai.jedis;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MikesOpenAiEmbeddingServiceWithVectorStore {
    private final VectorStore vectorStore;
    private final Map<String, Object> metaData = Map.of("country", "unknown",
            "year", 1900);
    // Describe songs with enough detail for semantic similarity to work
    private final List<Document> songs = List.of(
            new Document("Bohemian Rhapsody by Queen - epic rock opera with dramatic shifts, piano, guitar solos, and operatic vocals", metaData),
            new Document("Hotel California by Eagles - melodic rock with layered guitars, mysterious lyrics, and a classic slow groove", metaData),
            new Document("Stairway to Heaven by Led Zeppelin - progressive rock ballad building from acoustic guitar to powerful electric climax", metaData),
            new Document("Comfortably Numb by Pink Floyd - psychedelic rock with iconic guitar solo, melancholic mood, and dreamy atmosphere", metaData),
            new Document("November Rain by Guns N Roses - power ballad with orchestral arrangement, emotional piano, and soaring guitar", metaData),
            new Document("Africa by Toto - upbeat pop rock with catchy melody, lush keyboards, and tropical rhythms", metaData),
            new Document("Eye of the Tiger by Survivor - driving rock anthem with punchy guitar riff, high energy, and motivational lyrics", metaData),
            new Document("Don't Stop Believin by Journey - uplifting rock anthem with piano intro, powerful chorus, and feel-good energy", metaData),
            new Document("Sweet Child O Mine by Guns N Roses - hard rock with iconic guitar riff, emotional vocals, and energetic build", metaData),
            new Document("More Than a Feeling by Boston - classic rock with layered guitars, soaring vocals, and nostalgic dreamy feel", metaData),
            new Document("Smells Like Teen Spirit by Nirvana - grunge anthem with loud-quiet-loud dynamics, distorted guitars, and rebellious energy", metaData),
            new Document("Purple Rain by Prince - slow-burning ballad with soulful vocals, bluesy guitar, and deeply emotional atmosphere", metaData),
            new Document("Under the Bridge by Red Hot Chili Peppers - melodic rock with introspective lyrics, clean verses, and lush orchestral chorus", metaData),
            new Document("Black by Pearl Jam - emotional grunge ballad with powerful vocals, acoustic guitar, and themes of loss and longing", metaData),
            new Document("Creep by Radiohead - alternative rock with soft verses exploding into distorted chorus, melancholic and self-deprecating", metaData),
            new Document("Nothing Else Matters by Metallica - gentle acoustic ballad with orchestral strings, introspective lyrics, and rare vulnerability", metaData),
            new Document("With or Without You by U2 - atmospheric rock with slow build, pulsing bass, and Bono's soaring longing vocals", metaData),
            new Document("Wonderwall by Oasis - Britpop classic with strummed acoustic guitar, singalong chorus, and nostalgic romantic feel", metaData),
            new Document("Dancing in the Dark by Bruce Springsteen - upbeat heartland rock with synths, driving beat, and themes of restlessness", metaData),
            new Document("Wish You Were Here by Pink Floyd - acoustic driven rock with melancholic tone, gentle fingerpicking, and themes of absence", metaData)
    );

    @PostConstruct
    public void preloadEmbeddings() {
        vectorStore.add(songs);
    }

    public List<Document> recommendNextSong(String justListenedTo) {
        List<Document> results = this.vectorStore.similaritySearch(
                SearchRequest.builder().query(justListenedTo).similarityThreshold(0.92).topK(3).build());
        for (Document document : results) {
            log.info("Song: {} Score {} Distance {}", document.getText(), document.getScore(), document.getMetadata());
        }
        log.info("Results: {}", results);
        return results;
    }
}
