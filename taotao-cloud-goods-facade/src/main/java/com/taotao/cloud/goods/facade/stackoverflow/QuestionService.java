package com.taotao.cloud.goods.facade.stackoverflow;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

// src/main/java/com/example/client/stackoverflow/QuestionService.java
public interface QuestionService {
    
    @GetExchange("/questions")
    QuestionsResponse getQuestions(
        @RequestParam(required = false) String tagged,
        @RequestParam(defaultValue = "desc") String order,
        @RequestParam(defaultValue = "activity") String sort,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "30") int pagesize
    );
    
    @GetExchange("/questions/{questionId}")
    QuestionDetail getQuestion(
        @PathVariable Long questionId,
        @RequestParam(required = false) String site
    );
}
