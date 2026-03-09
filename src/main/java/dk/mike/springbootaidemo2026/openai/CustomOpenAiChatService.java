package dk.mike.springbootaidemo2026.openai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class OpenAiChatService {
    private final OpenAiChatModel chatModel;

    public ChatResponse generateAnswer(String question) {
        String prompt = generatePrompt(question);
        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt,
                        OpenAiChatOptions.builder()
                                .model("gpt-4o")
//                                .responseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, this.jsonSchema))
                                .maxTokens(1500)  // Use maxTokens for non-reasoning models
                                .build()
                ));
        return response;
    }

    String generatePrompt(String question) {
        String context = getDeveloperComparisonJson();
        return """                
                Answer the following question based ONLY on the provided context:
                %s
                
                Question: %s
                
                """.formatted(context, question);
    }

    public String getDeveloperComparisonJson() {
        try {
            ClassPathResource resource = new ClassPathResource("developer_comparison.json");
            try (InputStream inputStream = resource.getInputStream()) {
                return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read developer_comparison.json", e);
        }
    }

}
