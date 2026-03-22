package dk.mike.springbootaidemo2026.openai.embedding;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@Slf4j
public class EmbeddingModelConfiguration {
    @Bean
    public OpenAiApi openAiApi() {
        return OpenAiApi.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .build();
    }

    @Bean
    public OpenAiEmbeddingModel getOpenAiEmbeddingModel(OpenAiApi openAiApi) {
        return new OpenAiEmbeddingModel(
                openAiApi,
                MetadataMode.EMBED,
                OpenAiEmbeddingOptions.builder()
//                        .model("text-embedding-ada-002")
                        .model("text-embedding-3-small")
                        .user("Mike")
                        .build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE);
    }
}
