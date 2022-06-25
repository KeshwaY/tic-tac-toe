package com.github.keshway.game.round;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.event.PlayerMove;
import com.github.keshway.databus.event.RoundEnd;

import java.util.Map;
import java.util.function.Consumer;

class WithinRound extends BaseRound {
    private final Board board;
    private final Map<Class<?>, Consumer<GameEvent>> events;

    WithinRound(Board board, DataBus<GameEvent> dataBus) {
        super(dataBus);
        this.board = board;
        this.events = Map.of(
                PlayerMove.class, this::handlePlayerMove,
                RoundEnd.class, this::handleRoundEnd
        );
    }

    @Override
    public void accept(GameEvent event) {
        Class<?> eventClass = event.getClass();
        if (!events.containsKey(eventClass)) return;
        events.get(eventClass).accept(event);
    }

    private void handlePlayerMove(GameEvent gameEvent) {
        PlayerMove playerMove = (PlayerMove) gameEvent;
        PlayerType player = playerMove.player();
        Index index = playerMove.index();
        board.put(player, index);
    }

    private void handleRoundEnd(GameEvent gameEvent) {
        next();
    }

    @Override
    public Round next() {
        dataBus.unsubscribe(this);
        return new PreRound(dataBus, board.size());
    }
}
