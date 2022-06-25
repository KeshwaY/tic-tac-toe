package com.github.keshway.game.round;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.Size;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.event.RoundStart;

class PreRound extends BaseRound {
    private final Board board;

    PreRound(DataBus<GameEvent> dataBus, Size size) {
        super(dataBus);
        this.board = new GameBoard(size);
    }

    @Override
    public void accept(GameEvent event) {
        if (event.getClass().equals(RoundStart.class)) {
            next();
        }
    }

    @Override
    public Round next() {
        dataBus.unsubscribe(this);
        return new WithinRound(board, dataBus);
    }
}
