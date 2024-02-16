package com.server.exception.custom;

public class UserExistAlready extends RuntimeException{

    public UserExistAlready(String messge){
        super(messge);
    }

}
