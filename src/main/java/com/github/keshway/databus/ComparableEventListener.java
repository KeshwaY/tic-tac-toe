package com.github.keshway.databus;

public interface ComparableEventListener<E extends Event> extends
        EventListener<E>, Comparable<ComparableEventListener<E>> {
}
