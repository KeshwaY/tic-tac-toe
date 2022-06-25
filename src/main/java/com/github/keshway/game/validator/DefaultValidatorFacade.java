package com.github.keshway.game.validator;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.GameConfiguration;
import com.github.keshway.databus.component.Result;
import com.github.keshway.databus.component.Size;
import com.github.keshway.databus.event.GameEvent;

public class DefaultValidatorFacade implements ValidatorFacade {
    @Override
    public Result initialize(GameConfiguration configuration, DataBus<GameEvent> gameBus) {
        MoveValidator moveValidator = createMoveValidator(configuration);
        gameBus.subscribe(moveValidator);
        return new Result(true);
    }

    private MoveValidator createMoveValidator(GameConfiguration configuration) {
        Size size = configuration.boardDimensions().size();
        MaxMoveIndex maxMoveIndex = new MaxMoveIndex(size.value());
        return new DefaultMoveValidator(
                maxMoveIndex,
                new PlayerMoveHistory()
        );
    }
}
