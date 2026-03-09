package dk.mike.springbootaidemo2026.openai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OpenAiChatServiceTest {
    @Autowired
    private OpenAiChatService openAiChatService;

    @Test
    void generateAnswer() {
        ChatResponse chatResponse = openAiChatService.generateAnswer("What are java developers usually good at withing sports?");
        System.out.println(chatResponse.getResult().getOutput().getText());
    }

    @Test
    void generateAnswerDotNet() {
        ChatResponse chatResponse = openAiChatService.generateAnswer("Do a comparison between python and java developers regarding skills withing sports?");
        System.out.println(chatResponse.getResult().getOutput().getText());
    }
}