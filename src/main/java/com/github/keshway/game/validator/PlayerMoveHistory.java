package com.github.keshway.game.validator;

import com.github.keshway.databus.component.Index;

import java.util.HashSet;
import java.util.Set;

class PlayerMoveHistory implements MoveHistory {
    private final Set<Index> indexes;

    public PlayerMoveHistory() {
        indexes = new HashSet<>();
    }

    @Override
    public Move makeMove(Index index) {
        return new Move(index, indexes.add(index));
    }

    @Override
    public MoveHistory reset() {
        return new PlayerMoveHistory();
    }

    @Override
    public boolean contains(Index index) {
        return indexes.contains(index);
    }
}
