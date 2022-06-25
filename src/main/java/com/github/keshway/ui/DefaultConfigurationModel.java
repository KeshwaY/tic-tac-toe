package com.github.keshway.ui;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.*;
import com.github.keshway.databus.event.ConfigurationEvent;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.response.ConfigurationResponse;

final class DefaultConfigurationModel implements ConfigurationModel {

    private final DataBus<ConfigurationEvent> configurationBus;
    private final MessageBundle messageBundle;
    private DataBus<GameEvent> gameBus;
    private Result wasConfigured;

    private BoardDimensions boardDimensions;
    private WinCondition winCondition;
    private PlayOrder playOrder;
    private final RoundCount roundCount;
    private Players players;

    DefaultConfigurationModel(DataBus<ConfigurationEvent> configurationBus, MessageBundle messageBundle) {
        this.configurationBus = configurationBus;
        this.messageBundle = messageBundle;
        this.wasConfigured = new Result(false);
        this.roundCount = new RoundCount(3);
    }

    @Override
    public void accept(ConfigurationResponse response) {
        wasConfigured = response.result();
        if (wasConfigured.wasSuccessful()) {
            this.gameBus = response.gameBus()
                    .orElseThrow();
        }
    }

    @Override
    public void setBoardDimensions(BoardDimensions boardDimensions) {
        this.boardDimensions = boardDimensions;
    }

    @Override
    public void setWinCondition(WinCondition winCondition) {
        this.winCondition = winCondition;
    }

    @Override
    public void setPlayOrder(PlayOrder playOrder) {
        this.playOrder = playOrder;
    }

    @Override
    public void setPlayers(Players players) {
        this.players = players;
    }

    @Override
    public Result wasConfigured() {
        return wasConfigured;
    }

    @Override
    public DataBus<GameEvent> gameBus() {
        return gameBus;
    }

    @Override
    public MessageBundle messageBundle() {
        return messageBundle;
    }

    @Override
    public PlayOrder playOrder() {
        return playOrder;
    }

    @Override
    public Players players() {
        return players;
    }

    @Override
    public BoardDimensions dimensions() {
        return boardDimensions;
    }

    @Override
    public void configureGame() {
        GameConfiguration gameConfiguration = new GameConfiguration(
                playOrder,
                roundCount,
                winCondition,
                boardDimensions
        );
        configurationBus.publish(new ConfigurationEvent(gameConfiguration, this));
    }

    @Override
    public WinCondition maxWinCondition() {
        return new WinCondition(
                Math.max(boardDimensions.length().value(), boardDimensions.height().value())
        );
    }
}
