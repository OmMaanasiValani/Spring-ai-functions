package com.example.springaifunctions.controller;


import com.example.springaifunctions.service.SpringAIFunctionService;
import lombok.RequiredArgsConstructor;
import model.Answer;

import model.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringAIFunctionsController {

    @Autowired
    private SpringAIFunctionService springAIFunctionService;

    /*
    Retrieves response from OpenAI LLM
     */
    @PostMapping("/ask")
    public Answer getAnswers(@RequestBody Question question){
        return springAIFunctionService.getAnswers(question.question());
    }


}
