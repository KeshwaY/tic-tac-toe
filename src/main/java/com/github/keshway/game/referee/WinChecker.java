package com.github.keshway.game.referee;

import com.github.keshway.databus.component.Index;

import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;

class WinChecker implements Checker{
    private final Collection<Set<Index>> winConditions;

    WinChecker(Collection<Set<Index>> winConditions) {
        this.winConditions = winConditions;
    }

    @Override
    public Move check(SortedSet<Index> moves) {
        return new Move(
                winConditions.stream()
                        .anyMatch(moves::containsAll)
        );
    }
}
