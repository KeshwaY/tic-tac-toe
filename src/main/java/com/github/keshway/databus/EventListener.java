package com.github.keshway.databus;

public interface EventListener<E extends Event> {
    void accept(E event);
}
