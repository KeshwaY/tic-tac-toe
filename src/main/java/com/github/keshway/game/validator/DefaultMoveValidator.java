package com.github.keshway.game.validator;

import com.github.keshway.databus.ComparableEventListener;
import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.Result;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.event.PlayerMove;
import com.github.keshway.databus.event.RoundEnd;
import com.github.keshway.databus.response.GameEventResponse;

import java.util.Map;
import java.util.function.Consumer;

class DefaultMoveValidator implements MoveValidator {

    private MoveHistory playerMoveHistory;
    private final MaxMoveIndex maxMoveIndex;
    private final Map<Class<?>, Consumer<GameEvent>> events;

    DefaultMoveValidator(MaxMoveIndex maxMoveIndex, MoveHistory playerMoveHistory) {
        this.maxMoveIndex = maxMoveIndex;
        this.playerMoveHistory = playerMoveHistory;
        this.events = Map.of(
                PlayerMove.class, this::validateMove,
                RoundEnd.class, this::reset
        );
    }

    @Override
    public void accept(GameEvent event) {
        Class<?> eventClass = event.getClass();
        if (!events.containsKey(eventClass)) return;
        events.get(eventClass).accept(event);
    }

    private void validateMove(GameEvent event) {
        PlayerMove playerMove = (PlayerMove) event;
        Index moveIndex = playerMove.index();
        Result result = getResult(playerMove, moveIndex);
        playerMove.reply(new GameEventResponse(result));
    }

    private Result getResult(PlayerMove event, Index moveIndex) {
        if (!maxMoveIndex.checkViability(moveIndex).wasSuccessful() || playerMoveHistory.contains(moveIndex)) {
            event.remove();
            return new Result(false);
        }
        return makeMove(moveIndex);
    }

    private Result makeMove(Index moveIndex) {
        Move move = playerMoveHistory.makeMove(moveIndex);
        return new Result(move.isValid());
    }

    private void reset(GameEvent event) {
        this.playerMoveHistory = playerMoveHistory.reset();
    }

    @Override
    public int compareTo(ComparableEventListener<GameEvent> o) {
        return 1;
    }
}
