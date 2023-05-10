package com.enfusesolutions.newsapi.dto;

import lombok.Data;

import java.util.LinkedHashMap;
@Data
public class Source {
    private String id;
    private String name;
    private LinkedHashMap<String, String> additionalProperties = new LinkedHashMap<>();
}