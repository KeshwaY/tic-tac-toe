package com.github.keshway.ui;

import com.github.keshway.databus.ComparableEventListener;
import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.component.*;
import com.github.keshway.databus.event.*;
import com.github.keshway.databus.response.GameEventResponse;

import java.util.Map;
import java.util.function.Consumer;

class GameModel implements Model, ComparableEventListener<GameEvent>, ResponseListener<GameEventResponse> {

    private final DataBus<GameEvent> dataBus;
    private final MessageBundle messageBundle;
    private final Players players;
    private PlayerType player;
    private PlayerScores playerScores;
    private PlayOrder playOrder;
    private Result lastMove;
    private RoundStatus roundStatus;
    private GameStatus gameStatus;

    private final Map<Class<?>, Consumer<GameEvent>> events;

    GameModel(DataBus<GameEvent> dataBus, Players players, PlayOrder playOrder, MessageBundle messageBundle) {
        this.dataBus = dataBus;
        this.messageBundle = messageBundle;
        this.players = players;
        this.player = players.first().player();
        this.playOrder = playOrder;
        this.playerScores = new PlayerScores(
                new PlayerScore(PlayerType.PLAYER_O, new Score(0)),
                new PlayerScore(PlayerType.PLAYER_X, new Score(0))
        );
        this.gameStatus = new GameStatus(false);
        this.lastMove = new Result(false);
        this.roundStatus = new RoundStatus(false);
        this.events = Map.of(
                RoundResult.class, this::handleRoundResult,
                RoundEnd.class, this::handleRoundEnd,
                GameEnd.class, this::handleGameEnd
        );
    }

    void makeAMove(Index index) {
        PlayerMove playerMove = new PlayerMove(
                index,
                player,
                this
        );
        dataBus.publish(playerMove);
    }

    void consumeMove() {
        this.lastMove = new Result(false);
    }

    void consumeResult() {
        this.roundStatus = new RoundStatus(false);
    }

    @Override
    public void accept(GameEvent event) {
        Class<?> eventClass = event.getClass();
        if (!events.containsKey(eventClass)) return;
        events.get(eventClass).accept(event);
    }

    public void handleRoundEnd(GameEvent event) {
        this.roundStatus = new RoundStatus(true);
        PlayerType newStarter = playOrder.whoStarts().getOpponent();
        this.playOrder = new PlayOrder(newStarter);
        this.player = newStarter;
    }

    public void handleRoundResult(GameEvent event) {
        RoundResult roundResult = (RoundResult) event;
        this.playerScores = roundResult.getPlayerScores();
    }

    private void handleGameEnd(GameEvent gameEvent) {
        this.gameStatus = new GameStatus(true);
    }

    @Override
    public void accept(GameEventResponse response) {
        this.lastMove = response.result();
        if (lastMove.wasSuccessful()){
            this.player = player.getOpponent();
        }
    }

    // TODO: I have no idea how to write frontend
    PlayerType getPlayer() {
        return player;
    }

    Players getPlayers() {
        return players;
    }

    PlayerScores getPlayerScores() {
        return playerScores;
    }

    MessageBundle getMessageBundle() {
        return messageBundle;
    }

    Result getLastMove() {
        return lastMove;
    }

    RoundStatus getRoundStatus() {
        return roundStatus;
    }

    GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public int compareTo(ComparableEventListener<GameEvent> o) {
        return 99;
    }

}
