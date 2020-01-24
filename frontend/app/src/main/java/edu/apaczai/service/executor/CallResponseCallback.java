package edu.apaczai.service.executor;

public interface CallResponseCallback<T> {
    void callback(T result);
}
