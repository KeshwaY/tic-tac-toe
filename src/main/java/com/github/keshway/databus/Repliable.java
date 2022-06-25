package com.github.keshway.databus;

public interface Repliable<R> {
    void reply(R response);
}
