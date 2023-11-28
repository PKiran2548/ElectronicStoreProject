package com.example.curd.opperation.exception;

public class ResourceNotFoundException extends RuntimeException{

    private String resourceName ;

    private String filedName ;

    private String filedValue ;

    public ResourceNotFoundException(String resourceName, String filedName, String filedValue) {
        super(String.format("%s resource not found %s : %s", resourceName, filedName, filedValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }

    public ResourceNotFoundException(String resourceName) {
    }
}
