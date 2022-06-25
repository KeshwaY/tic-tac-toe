package com.github.keshway.game.referee;

import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.Length;
import com.github.keshway.databus.component.Size;
import com.github.keshway.databus.component.WinCondition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO: somehow refactor this if time allows
class CheckerFactory {
    public WinChecker horizontal(Length length, Size size, WinCondition winCondition) {
        Collection<Set<Index>> winConditions = new HashSet<>();
        IntStream.range(0, size.value()).boxed()
                .forEach(i -> {
                    int horMax = i + winCondition.value() - 1;
                    if ( (horMax < size.value()) && ( (i / length.value()) == (horMax / length.value())) ) {
                        winConditions.add(
                                IntStream.range(i, horMax + 1).boxed()
                                        .map(Index::new)
                                        .collect(Collectors.toSet())
                        );
                    }
                });
        return new WinChecker(winConditions);
    }

    public WinChecker vertical(Length length, Size size, WinCondition winCondition) {
        Collection<Set<Index>> winConditions = new HashSet<>();
        IntStream.range(0, size.value()).boxed()
                .forEach(i -> {
                    int vertMax = i + (winCondition.value() - 1) * length.value();
                    if (vertMax < size.value()) {
                        winConditions.add(
                                IntStream.iterate(i, (j) -> j <= vertMax, (j) -> j + length.value()).boxed()
                                        .map(Index::new)
                                        .collect(Collectors.toSet())
                        );
                    }
                });
        return new WinChecker(winConditions);
    }

    public WinChecker diagonal(Length length, Size size, WinCondition winCondition) {
        Collection<Set<Index>> winConditions = new HashSet<>();
        IntStream.range(0, size.value()).boxed()
                .forEach(i -> {
                    int diagMax =  i + (winCondition.value() - 1) * (length.value() + 1);
                    if ( (diagMax < size.value()) && (i / length.value() +
                            (winCondition.value() - 1) == (diagMax / length.value())) ) {
                        winConditions.add(
                                IntStream.iterate(i, (j) -> j <= diagMax, (j) -> j + length.value() + 1).boxed()
                                        .map(Index::new)
                                        .collect(Collectors.toSet())
                        );
                    }
                });
        return new WinChecker(winConditions);
    }

    public WinChecker antiDiagonal(Length length, Size size, WinCondition winCondition) {
        Collection<Set<Index>> winConditions = new HashSet<>();
        IntStream.range(0, size.value()).boxed()
                .forEach(i -> {
                    int antiMax = i + (winCondition.value() - 1) * (length.value() - 1) ;
                    if ( (antiMax < size.value()) && (i / length.value() +
                            (winCondition.value() - 1) == (antiMax / length.value())) ) {
                        winConditions.add(
                                IntStream.iterate(i, (j) -> j <= antiMax, (j) -> j + length.value() - 1).boxed()
                                        .map(Index::new)
                                        .collect(Collectors.toSet())
                        );
                    }
                });
        return new WinChecker(winConditions);
    }
}
