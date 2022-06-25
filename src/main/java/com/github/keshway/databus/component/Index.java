package com.github.keshway.databus.component;

public record Index(int value) implements Comparable<Index> {
    @Override
    public int compareTo(Index o) {
        return Integer.compare(value, o.value);
    }
}
