package com.github.keshway.game.match;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.GameConfiguration;
import com.github.keshway.databus.component.Result;
import com.github.keshway.databus.component.RoundCount;
import com.github.keshway.databus.event.GameEvent;

public class DefaultMatchFacade implements MatchFacade {

    private final MatchController matchController;

    public DefaultMatchFacade() {
        this.matchController = new DefaultMatchController(
                new BasicScoreCounter(),
                new RoundCounter(new RoundCount(3))
        );
    }

    @Override
    public Result initialize(GameConfiguration configuration, DataBus<GameEvent> gameBus) {
        gameBus.subscribe(matchController);
        return new Result(true);
    }
}
