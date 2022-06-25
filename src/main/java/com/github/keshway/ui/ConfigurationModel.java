package com.github.keshway.ui;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.component.BoardDimensions;
import com.github.keshway.databus.component.PlayOrder;
import com.github.keshway.databus.component.Result;
import com.github.keshway.databus.component.WinCondition;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.response.ConfigurationResponse;

interface ConfigurationModel extends Model, ResponseListener<ConfigurationResponse> {
    void setBoardDimensions(BoardDimensions boardDimensions);
    void setWinCondition(WinCondition winCondition);
    void setPlayOrder(PlayOrder playOrder);
    void setPlayers(Players players);
    Result wasConfigured();
    void configureGame();
    WinCondition maxWinCondition();
    DataBus<GameEvent> gameBus();
    MessageBundle messageBundle();
    PlayOrder playOrder();
    Players players();
    BoardDimensions dimensions();
}
