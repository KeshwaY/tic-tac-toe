package com.github.keshway.databus.component;

public record Score(int value) {
    public Score add(Score score) {
        return new Score(value + score.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
