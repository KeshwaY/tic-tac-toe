package com.github.keshway.databus.component;

public enum ResultType {
    WIN(new Score(3)) {
        @Override
        ResultType getOpposite() {
            return DEFEAT;
        }
    },
    DEFEAT(new Score(0)) {
        @Override
        ResultType getOpposite() {
            return WIN;
        }
    },
    DRAW(new Score(1)) {
        @Override
        ResultType getOpposite() {
            return this;
        }
    };

    private final Score value;

    ResultType(Score scoresToAdd) {
        this.value = scoresToAdd;
    }

    public Score value() {
        return value;
    }

    abstract ResultType getOpposite();
}
