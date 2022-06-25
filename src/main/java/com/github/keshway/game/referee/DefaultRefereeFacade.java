package com.github.keshway.game.referee;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.*;
import com.github.keshway.databus.event.GameEvent;

import java.util.List;

public class DefaultRefereeFacade implements RefereeFacade {

    @Override
    public Result initialize(GameConfiguration configuration, DataBus<GameEvent> gameBus) {
        Size size = configuration.boardDimensions()
                .size();
        Length length = configuration.boardDimensions()
                .length();
        WinCondition winCondition = configuration.winCondition();
        CheckerFactory checkerFactory = new CheckerFactory();
        List<Checker> checkers = createCheckers(size, length, winCondition, checkerFactory);
        Referee referee = new GameReferee(size, checkers);
        gameBus.subscribe(referee);
        return new Result(true);
    }

    private List<Checker> createCheckers(Size size, Length length, WinCondition winCondition, CheckerFactory checkerFactory) {
        return List.of(
                checkerFactory.horizontal(length, size, winCondition),
                checkerFactory.vertical(length, size, winCondition),
                checkerFactory.diagonal(length, size, winCondition),
                checkerFactory.antiDiagonal(length, size, winCondition)
        );
    }
}
