package com.ecomm.railway;

public class Failure<T> extends Result<T>{
    private final String error;
    public Failure(String error){
        this.error = error;
    }

    @Override public boolean isSuccess(){
        return false;
    }
    @Override public T get(){
        throw new RuntimeException(error);
    }
    @Override public String getError(){
        return error;
    }

}
