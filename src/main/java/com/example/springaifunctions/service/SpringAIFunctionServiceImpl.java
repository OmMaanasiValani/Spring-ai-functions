package com.example.springaifunctions.service;


import com.example.springaifunctions.config.Config;
import com.example.springaifunctions.functions.StockPriceFunction;
import com.example.springaifunctions.model.StockPriceRequest;
import com.example.springaifunctions.model.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import model.Answer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.model.tool.ToolExecutionResult;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpringAIFunctionServiceImpl implements SpringAIFunctionService {


    private final ChatClient chatClient;

    @Value("${sfg.aiapp.ninjaapikey}")
    private String apiNinjasKey;

    private final Config config;

    private final ToolCallingManager toolCallingManager;

   /*
       The method prompts openAI llm with userMessage and promptOptions which has callback to functions
    */
    @Override
    public Answer getAnswers(String question) {
/*
        StockPriceFunction function = new StockPriceFunction(apiNinjasKey) ;
        StockPriceResponse res =  function.apply(new StockPriceRequest("AAPL"));
        System.out.println("res: "+res.change());*/

        //ToolCallback[] tools = ToolCallbacks.from(config);

       // OpenAiChatOptions options = OpenAiChatOptions.builder().toolCallbacks(tools).toolChoice("required").build();

        Message systemMessage = new SystemPromptTemplate(
                "You are a stock price assistant. Provide brief about the company for which the request is made by user.Always call the StockPriceFunction tool to fetch prices. " +
                        "Do not answer directly; invoke the tool."
        ).createMessage();

        Message userMessage = new PromptTemplate(question).createMessage();

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));


        System.out.println("userMessage: "+userMessage);
        System.out.println("systemMessage: "+systemMessage);

        return new Answer(chatClient
                .prompt(prompt)
                .tools(config)
                .call().content());
    }

}
