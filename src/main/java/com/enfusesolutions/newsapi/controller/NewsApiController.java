package com.enfusesolutions.newsapi.controller;

import com.enfusesolutions.newsapi.dto.Article;
import com.enfusesolutions.newsapi.service.NewsApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/news/api")
@RequiredArgsConstructor
@Validated
public class NewsApiController {

    private final NewsApiService newsApiService;

    @GetMapping
    public List<Article> getNews(@RequestParam("query") @NotBlank String query,
                                 @RequestParam("fromDate") @NotBlank String fromDate,
                                 @RequestParam("sortBy") @NotBlank String sortBy) {
        return newsApiService.getArticles(query,fromDate,sortBy);
    }
}
