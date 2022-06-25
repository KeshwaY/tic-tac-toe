package com.github.keshway.databus;

interface Subscribable<E extends Event> {
    void subscribe(DataBus<E> dataBus);
}
