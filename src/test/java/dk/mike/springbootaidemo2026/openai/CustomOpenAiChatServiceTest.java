package dk.mike.springbootaidemo2026.openai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CustomOpenAiChatServiceTest {
    @Autowired
    private CustomOpenAiChatService customOpenAiChatService;

    @Test
    void generateAnswer() {
        ChatResponse chatResponse = customOpenAiChatService
                .generateAnswer("What are java developers usually good at withing sports?");
        System.out.println(chatResponse.getResult().getOutput().getText());
    }

    @Test
    void generateAnswerDotNet() {
        ChatResponse chatResponse = customOpenAiChatService
                .generateAnswer("Do a comparison between python and .net developers " +
                        "regarding skills withing sports?");
        System.out.println(chatResponse.getResult().getOutput().getText());
    }

    @Test
    void generateAnswerArchery() {
        ChatResponse chatResponse = customOpenAiChatService
                .generateAnswer("Which developer is best at archery and badminton in game of combining these disciplines?");
        System.out.println(chatResponse.getResult().getOutput().getText());
    }
}