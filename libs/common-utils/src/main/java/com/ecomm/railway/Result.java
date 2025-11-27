package com.ecomm.railway;

import java.util.function.Function;

public abstract class Result<T>{
    public abstract boolean isSuccess();
    public abstract T get();
    public abstract String getError();

    public <U> Result<U> bind(Function<T,Result<U>> func){
        if(!isSuccess())
            return Result.failure(getError());
        return func.apply(get());
    }

    public static <T> Result<T> success(T value){
        return new Success<>(value);
    }
    public static <T> Result<T> failure(String error){
        return new Failure<>(error);
    }
}
