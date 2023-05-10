package com.enfusesolutions.newsapi.excptions;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String msg){
        super(msg);
    }
}
