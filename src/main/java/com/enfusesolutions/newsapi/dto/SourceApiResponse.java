package com.enfusesolutions.newsapi.dto;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Data
public class SourceApiResponse {
    private String status;
    private String code;
    private String message;
    private Integer totalResults;
    private List<Article> articles;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
}