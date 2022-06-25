package com.github.keshway.databus;

import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingDeque;

final class EventPipe<E extends Event> implements Pipe<E> {
    private final Deque<E> events = new LinkedBlockingDeque<>();

    @Override
    public int size() {
        return events.size();
    }

    @Override
    public Optional<E> peek() {
        return Optional.ofNullable(events.peek());
    }

    @Override
    public Optional<E> poll() {
        return Optional.ofNullable(events.poll());
    }

    @Override
    public Optional<E> pollLast() {
        return Optional.ofNullable(events.pollLast());
    }

    @Override
    public void addFirst(E event) {
        events.addFirst(event);
    }

    @Override
    public void addLast(E event) {
        events.addLast(event);
    }

    @Override
    public boolean remove(E event) {
        return events.remove(event);
    }

    @Override
    public boolean contains(E event) {
        return events.contains(event);
    }
}
