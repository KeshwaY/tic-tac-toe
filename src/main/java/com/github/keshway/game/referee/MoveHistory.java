package com.github.keshway.game.referee;

import com.github.keshway.databus.component.Index;

import java.util.List;

interface MoveHistory {
    Move makeMove(Index index, List<Checker> checker);
    MoveHistory reset();
}
