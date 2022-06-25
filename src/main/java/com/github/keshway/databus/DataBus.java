package com.github.keshway.databus;

public interface DataBus<E extends Event> {
    void publish(E event);
    void subscribe(EventListener<E> dataListener);
    void unsubscribe(EventListener<E> dataListener);
}
