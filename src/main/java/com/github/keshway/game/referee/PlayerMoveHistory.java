package com.github.keshway.game.referee;

import com.github.keshway.databus.component.Index;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

class PlayerMoveHistory implements MoveHistory {
    private final SortedSet<Index> moves;

    PlayerMoveHistory() {
        moves = new TreeSet<>();
    }

    @Override
    public Move makeMove(Index index, List<Checker> checker) {
        moves.add(index);
        return checker.stream()
                .map(c -> c.check(moves))
                .filter(Move::won)
                .findFirst()
                .orElse(new Move(false));
    }

    @Override
    public MoveHistory reset() {
        return null;
    }
}
