package com.github.keshway.game.validator;

import com.github.keshway.databus.component.Index;

interface MoveHistory {
    Move makeMove(Index index);
    MoveHistory reset();
    boolean contains(Index index);
}
