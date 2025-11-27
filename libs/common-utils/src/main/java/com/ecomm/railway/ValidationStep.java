package com.ecomm.railway;

public interface ValidationStep<T> {
    Result<T> validate(T input);
}
