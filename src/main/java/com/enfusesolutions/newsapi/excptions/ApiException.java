package com.enfusesolutions.newsapi.excptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private final String errorMsg;
    public ApiException(String errorMsg){
        super(String.format("Error: %s",errorMsg));
        this.errorMsg = errorMsg;
    }
}
