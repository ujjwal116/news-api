package com.enfusesolutions.newsapi.dto;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
@Data
public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
}