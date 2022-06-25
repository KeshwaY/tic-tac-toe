package com.github.keshway.game.round;

class Empty implements Cell {
    @Override
    public Cell markAsPlayerO() {
        return new PlayerO();
    }

    @Override
    public Cell markAsPlayerX() {
        return new PlayerX();
    }

    @Override
    public Cell clear() {
        return this;
    }
}
