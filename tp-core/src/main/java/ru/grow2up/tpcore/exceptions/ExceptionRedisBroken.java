package ru.grow2up.tpcore.exceptions;

public class ExceptionRedisBroken extends RuntimeException{

    public ExceptionRedisBroken(String message) {
        super(message);
    }
}