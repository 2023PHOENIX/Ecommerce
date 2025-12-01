package com.ecomm.kafka;

public abstract class BaseKafkaConsumer<T> {

    protected void handle(T event) {
        try {
            consume(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void consume(T event);
}
