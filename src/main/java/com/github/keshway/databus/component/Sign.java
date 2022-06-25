package com.github.keshway.databus.component;

public record Sign(String value) {
    @Override
    public String toString() {
        return value;
    }
}
