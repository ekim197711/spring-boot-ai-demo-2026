package dk.mike.springbootaidemo2026.openai.embedding;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MikesOpenAiEmbeddingService {
    private final OpenAiEmbeddingModel embeddingModel;
    //    private final VectorStore vectorStore
    // Pre-built map of song title → embedding vector
    private final Map<String, float[]> songEmbeddings = new LinkedHashMap<>();

    // Describe songs with enough detail for semantic similarity to work
    private final List<String> songs = List.of(
            "Bohemian Rhapsody by Queen - epic rock opera with dramatic shifts, piano, guitar solos, and operatic vocals",
            "Hotel California by Eagles - melodic rock with layered guitars, mysterious lyrics, and a classic slow groove",
            "Stairway to Heaven by Led Zeppelin - progressive rock ballad building from acoustic guitar to powerful electric climax",
            "Comfortably Numb by Pink Floyd - psychedelic rock with iconic guitar solo, melancholic mood, and dreamy atmosphere",
            "November Rain by Guns N Roses - power ballad with orchestral arrangement, emotional piano, and soaring guitar",
            "Africa by Toto - upbeat pop rock with catchy melody, lush keyboards, and tropical rhythms",
            "Eye of the Tiger by Survivor - driving rock anthem with punchy guitar riff, high energy, and motivational lyrics",
            "Don't Stop Believin by Journey - uplifting rock anthem with piano intro, powerful chorus, and feel-good energy",
            "Sweet Child O Mine by Guns N Roses - hard rock with iconic guitar riff, emotional vocals, and energetic build",
            "More Than a Feeling by Boston - classic rock with layered guitars, soaring vocals, and nostalgic dreamy feel",
            "Smells Like Teen Spirit by Nirvana - grunge anthem with loud-quiet-loud dynamics, distorted guitars, and rebellious energy",
            "Purple Rain by Prince - slow-burning ballad with soulful vocals, bluesy guitar, and deeply emotional atmosphere",
            "Under the Bridge by Red Hot Chili Peppers - melodic rock with introspective lyrics, clean verses, and lush orchestral chorus",
            "Black by Pearl Jam - emotional grunge ballad with powerful vocals, acoustic guitar, and themes of loss and longing",
            "Creep by Radiohead - alternative rock with soft verses exploding into distorted chorus, melancholic and self-deprecating",
            "Nothing Else Matters by Metallica - gentle acoustic ballad with orchestral strings, introspective lyrics, and rare vulnerability",
            "With or Without You by U2 - atmospheric rock with slow build, pulsing bass, and Bono's soaring longing vocals",
            "Wonderwall by Oasis - Britpop classic with strummed acoustic guitar, singalong chorus, and nostalgic romantic feel",
            "Dancing in the Dark by Bruce Springsteen - upbeat heartland rock with synths, driving beat, and themes of restlessness",
            "Wish You Were Here by Pink Floyd - acoustic driven rock with melancholic tone, gentle fingerpicking, and themes of absence"
    );

    @PostConstruct
    public void preloadEmbeddings() {
        EmbeddingResponse response = embeddingModel.embedForResponse(songs);
        for (Embedding embedding : response.getResults()) {
            String songTitle = songs.get(embedding.getIndex());
            songEmbeddings.put(songTitle, embedding.getOutput());
        }
        System.out.println("Loaded " + songEmbeddings.size() + " song embeddings. "
                + "Tokens used: " + response.getMetadata().getUsage().getTotalTokens());
    }
 
    public String recommendNextSong(String justListenedTo) {
        // Embed the song the user just listened to
        float[] queryVector = embeddingModel.embed(justListenedTo);

        // Find the most similar song (excluding itself)
        return songEmbeddings.entrySet().stream()
                .filter(e -> !e.getKey().equalsIgnoreCase(justListenedTo))
                .max(Comparator.comparingDouble(e -> cosineSimilarity(queryVector, e.getValue())))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("No recommendation found"));
    }

    private double cosineSimilarity(float[] a, float[] b) {
        double dot = 0, normA = 0, normB = 0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

}
