package com.github.keshway.databus;

public interface ResponseListener<R> {
    void accept(R response);
}
