package com.github.keshway.game.round;

class PlayerO implements Cell {
    @Override
    public Cell markAsPlayerO() {
        return this;
    }

    @Override
    public Cell markAsPlayerX() {
        throw new IllegalStateException();
    }

    @Override
    public Cell clear() {
        return new Empty();
    }
}
