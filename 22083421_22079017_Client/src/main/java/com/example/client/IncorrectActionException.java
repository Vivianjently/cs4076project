package com.example.client;

public class IncorrectActionException extends Exception {
    public IncorrectActionException(){
        super();
    }
    public IncorrectActionException(String e){
        super(e);
    }

}