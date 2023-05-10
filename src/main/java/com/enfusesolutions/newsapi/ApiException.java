package com.enfusesolutions.newsapi;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private final String errorMsg;
    public ApiException(String errorMsg){
        super(String.format("Error: %s",errorMsg));
        this.errorMsg = errorMsg;
    }
}
