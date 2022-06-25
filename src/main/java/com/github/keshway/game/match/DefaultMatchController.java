package com.github.keshway.game.match;

import com.github.keshway.databus.ComparableEventListener;
import com.github.keshway.databus.component.PlayerScores;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.component.ResultType;
import com.github.keshway.databus.event.*;

import java.util.Map;
import java.util.function.Consumer;

// TODO: better name
class DefaultMatchController implements MatchController {
    private final ScoreCounter scoreCounter;
    private final RoundCounter roundCounter;
    private final Map<Class<?>, Consumer<GameEvent>> events;

    DefaultMatchController(ScoreCounter scoreCounter, RoundCounter roundCounter) {
        this.scoreCounter = scoreCounter;
        this.roundCounter = roundCounter;
        events = Map.of(
                RoundStart.class, this::handleRoundStart,
                RoundEnd.class, this::handleRoundEnd
        );
    }

    @Override
    public void accept(GameEvent event) {
        Class<?> eventClass = event.getClass();
        if (!events.containsKey(eventClass)) return;
        events.get(eventClass).accept(event);
    }

    private void handleRoundStart(GameEvent event) {
        GameStatus gameStatus = roundCounter.checkIfGameShouldEnd();
        if (gameStatus.shouldEnd()) {
            broadcastGameEnd(event);
        }
    }

    private void broadcastGameEnd(GameEvent event) {
        GameEnd gameEnd = new GameEnd(null);
        event.getDataBus()
                .publish(gameEnd);
    }

    private void handleRoundEnd(GameEvent event) {
        RoundEnd roundEnd = (RoundEnd) event;
        roundCounter.increment();
        ResultType result = roundEnd.result();
        PlayerType player = roundEnd.player();
        PlayerScores playerScores = scoreCounter.countScore(player, result);
        event.getDataBus()
                .publish(
                        new RoundResult(playerScores, null)
                );
        event.getDataBus()
                .publish(
                        new RoundStart(null)
                );
    }

    @Override
    public int compareTo(ComparableEventListener<GameEvent> o) {
        return 0;
    }
}
