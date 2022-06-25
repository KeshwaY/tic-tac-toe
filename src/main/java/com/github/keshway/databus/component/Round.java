package com.github.keshway.databus.component;

public record Round(int value) {
    public Round increment() {
        return new Round(value + 1);
    }
}
