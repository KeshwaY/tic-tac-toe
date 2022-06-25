package com.github.keshway.game.round;

interface Cell {
    Cell markAsPlayerO();
    Cell markAsPlayerX();
    Cell clear();
}
