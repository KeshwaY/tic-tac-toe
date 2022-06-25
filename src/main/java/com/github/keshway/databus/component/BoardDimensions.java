package com.github.keshway.databus.component;

public record BoardDimensions(Height height, Length length) {
    public Size size() {
        return new Size(height().value() * length().value());
    }
}
