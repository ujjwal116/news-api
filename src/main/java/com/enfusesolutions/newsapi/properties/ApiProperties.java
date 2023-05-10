package com.enfusesolutions.newsapi.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="api")
@Data
public class ApiProperties {
    private String hostUrl;
    private String apiKey;
    private int maxResults;
}
