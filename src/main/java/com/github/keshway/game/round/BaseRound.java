package com.github.keshway.game.round;

import com.github.keshway.databus.ComparableEventListener;
import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.event.GameEvent;

abstract class BaseRound implements Round {
    protected final DataBus<GameEvent> dataBus;

    BaseRound(DataBus<GameEvent> dataBus) {
        this.dataBus = dataBus;
        dataBus.subscribe(this);
    }

    @Override
    public int compareTo(ComparableEventListener<GameEvent> o) {
        return 2;
    }
}
