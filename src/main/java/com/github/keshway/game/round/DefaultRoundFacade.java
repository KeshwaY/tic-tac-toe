package com.github.keshway.game.round;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.GameConfiguration;
import com.github.keshway.databus.component.Result;
import com.github.keshway.databus.component.Size;
import com.github.keshway.databus.event.GameEvent;

public class DefaultRoundFacade implements RoundFacade {
    @Override
    public Result initialize(GameConfiguration configuration, DataBus<GameEvent> gameBus) {
        Size size = configuration.boardDimensions()
                .size();
        new PreRound(gameBus, size);
        return new Result(true);
    }
}
