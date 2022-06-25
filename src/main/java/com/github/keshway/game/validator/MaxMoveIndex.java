package com.github.keshway.game.validator;

import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.Result;

record MaxMoveIndex(int value) {
    Result checkViability(Index index) {
        return new Result(value > index.value());
    }
}
