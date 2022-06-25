package com.github.keshway.game.round;

class PlayerX implements Cell {
    @Override
    public Cell markAsPlayerO() {
        throw new IllegalStateException();
    }

    @Override
    public Cell markAsPlayerX() {
        return this;
    }

    @Override
    public Cell clear() {
        return new Empty();
    }
}
