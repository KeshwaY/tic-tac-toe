package com.github.keshway.databus.component;

public enum PlayerType {
    PLAYER_O(new Sign("O")) {
        @Override
        public PlayerType getOpponent() {
            return PLAYER_X;
        }
    },
    PLAYER_X(new Sign("X")) {
        @Override
        public PlayerType getOpponent() {
            return PLAYER_O;
        }
    };

    private final Sign sign;

    PlayerType(Sign sign) {
        this.sign = sign;
    }

    public Sign sign() {
        return sign;
    }

    public abstract PlayerType getOpponent();
}
