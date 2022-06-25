package com.github.keshway.databus;

import java.util.Optional;

interface Pipe<E> {
    Optional<E> peek();
    Optional<E> poll();
    Optional<E> pollLast();
    void addFirst(E event);
    void addLast(E event);
    boolean remove(E event);
    boolean contains(E event);
    int size();
}
