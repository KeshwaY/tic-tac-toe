package com.github.keshway.databus.implementation;

import com.github.keshway.databus.BaseDataBus;
import com.github.keshway.databus.event.GameEvent;

import java.util.concurrent.PriorityBlockingQueue;

final public class GameBus<E extends GameEvent> extends BaseDataBus<E> {
    public GameBus() {
        super(new PriorityBlockingQueue<>());
    }
}
