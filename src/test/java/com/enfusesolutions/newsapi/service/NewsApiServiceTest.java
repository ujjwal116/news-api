package com.enfusesolutions.newsapi.service;

import com.enfusesolutions.newsapi.ApiException;
import com.enfusesolutions.newsapi.ApplicationException;
import com.enfusesolutions.newsapi.dto.Article;
import com.enfusesolutions.newsapi.dto.SourceApiResponse;
import com.enfusesolutions.newsapi.properties.ApiProperties;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = NewsApiService.class)
@ActiveProfiles("test")
@EnableConfigurationProperties(value = ApiProperties.class)
public class NewsApiServiceTest {

    @Autowired
    private NewsApiService newsApiService;

    @MockBean
    private RestTemplate restTemplate;

    @Mock
    HttpClientErrorException httpClientErrorException;

    @Test
    void getArticles_ValidRequest_ReturnsArticles() {
        // Arrange
        String query = "some_QUERY";
        String fromDate = "2023-01-13";
        String sortBy = "publishedAt";

        SourceApiResponse response = new SourceApiResponse();
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());
        response.setArticles(articles);

        when(restTemplate.getForObject(any(), eq(SourceApiResponse.class))).thenReturn(response);

        // Act
        List<Article> result = newsApiService.getArticles(query, fromDate, sortBy);

        // Assert
        assertEquals(articles, result);
        verify(restTemplate, times(1)).getForObject(any(), eq(SourceApiResponse.class));
    }

    @Test
    void getArticles_ApiError_ThrowsApiException() {
        // Arrange
        String query = "tesla";
        String fromDate = "2023-01-13";
        String sortBy = "publishedAt";
        SourceApiResponse apiResponse =  new SourceApiResponse();
        apiResponse.setMessage("some error");
        when(httpClientErrorException.getResponseBodyAs(eq(SourceApiResponse.class))).thenReturn(apiResponse);
        when(restTemplate.getForObject(any(), eq(SourceApiResponse.class)))
                .thenThrow(httpClientErrorException);
        assertThrows(ApiException.class, () -> newsApiService.getArticles(query, fromDate, sortBy),"some error");
    }

    @Test
    void getArticles_GenericError_ThrowsApplicationException() {
        // Arrange
        String query = "tesla";
        String fromDate = "2023-01-13";
        String sortBy = "publishedAt";
        when(restTemplate.getForObject(any(), eq(SourceApiResponse.class))).thenThrow(new RuntimeException("Some error occurred."));
        assertThrows(ApplicationException.class, () -> newsApiService.getArticles(query, fromDate, sortBy));
    }
}
