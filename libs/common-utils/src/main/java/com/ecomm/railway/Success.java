package com.ecomm.railway;

public class Success<T> extends Result<T>{
    private final T value;
    public Success(T value){
        this.value = value;
    }

    @Override public boolean isSuccess(){return true;}
    @Override public T get(){ return this.value; }
    @Override public String getError(){return null;}
}
