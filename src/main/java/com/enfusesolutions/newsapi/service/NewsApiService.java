package com.enfusesolutions.newsapi.service;

import com.enfusesolutions.newsapi.ApiException;
import com.enfusesolutions.newsapi.ApplicationException;
import com.enfusesolutions.newsapi.dto.Article;
import com.enfusesolutions.newsapi.dto.SourceApiResponse;
import com.enfusesolutions.newsapi.properties.ApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class NewsApiService {
    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public List<Article> getArticles(String query, String fromDate, String sortBy) {
        log.debug("Received news request with params,query: {},fromDate: {},sortBy: {} ",query,fromDate,sortBy);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiProperties.getHostUrl())
                .queryParam("q", query + "&from=" + fromDate + "&sortBy=" + sortBy + "&apiKey="+apiProperties.getApiKey());
        try {
            SourceApiResponse response = restTemplate.getForObject(builder.buildAndExpand().toUri(), SourceApiResponse.class);
            assert response != null;
            log.info("received response successfully");
            if(response.getArticles().size()>=apiProperties.getMaxResults()){
                return response.getArticles().subList(0,apiProperties.getMaxResults()-1);
            }
            return response.getArticles();
        }catch (HttpClientErrorException e){
            log.error("Exception occurred while calling source news API, exception is: ",e);
            throw new ApiException(e.getResponseBodyAs(SourceApiResponse.class).getMessage());
        }catch (Exception e){
            log.error("Exception occurred while calling source news API, exception is: ",e);
            throw new ApplicationException(e.getMessage());
        }
    }
}
