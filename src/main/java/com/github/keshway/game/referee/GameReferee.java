package com.github.keshway.game.referee;

import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.component.ResultType;
import com.github.keshway.databus.component.Size;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.event.PlayerMove;
import com.github.keshway.databus.event.RoundEnd;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

class GameReferee extends BaseReferee {
    // TODO: wrap
    private final List<Checker> checkers;
    private Map<PlayerType, MoveHistory> playerMoves;
    private final Map<Class<?>, Consumer<GameEvent>> events;
    // TODO: move counter
    private final Size size;
    private int count = 0;

    GameReferee(Size size, List<Checker> checkers) {
        this.size = size;
        this.checkers = checkers;
        playerMoves = Map.of(
                PlayerType.PLAYER_X, new PlayerMoveHistory(),
                PlayerType.PLAYER_O, new PlayerMoveHistory()
        );
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


    void validateMove(GameEvent gameEvent) {
        PlayerMove playerMove = (PlayerMove) gameEvent;
        PlayerType player = playerMove.player();
        Index index = playerMove.index();
        MoveHistory moveHistory = playerMoves.get(player);
        Move move = moveHistory.makeMove(index, checkers);
        count++;
        if (move.won() || count == size.value()) {
            broadcastRoundEnd(player, move.won() ? ResultType.WIN: ResultType.DRAW, gameEvent);
        }
    }

    private void broadcastRoundEnd(PlayerType player, ResultType result, GameEvent gameEvent) {
        RoundEnd roundEnd = new RoundEnd(player, result, null);
        gameEvent.getDataBus()
                .publish(roundEnd);
    }

    void reset(GameEvent gameEvent) {
        playerMoves = Map.of(
                PlayerType.PLAYER_X, new PlayerMoveHistory(),
                PlayerType.PLAYER_O, new PlayerMoveHistory()
        );
        this.count = 0;
    }
}
