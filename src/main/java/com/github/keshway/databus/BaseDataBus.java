package com.github.keshway.databus;

import java.util.Collection;

public abstract class BaseDataBus<E extends Event> implements DataBus<E> {
    protected final Collection<EventListener<E>> listeners;
    private final Pipe<E> pipe = new EventPipe<>();

    public BaseDataBus(Collection<EventListener<E>> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void publish(E event) {
        event.setDataBus(this);
        event.setPipe(pipe);
        pipe.addFirst(event);
        listeners.forEach(l -> {
            if (! pipe.contains(event)) return;
            l.accept(event);
        });
        pipe.remove(event);
    }

    @Override
    public void subscribe(EventListener<E> dataListener) {
        listeners.add(dataListener);
    }

    @Override
    public void unsubscribe(EventListener<E> dataListener) {
        listeners.remove(dataListener);
    }
}
