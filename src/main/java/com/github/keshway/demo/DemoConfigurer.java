package com.github.keshway.demo;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.component.BoardDimensions;
import com.github.keshway.databus.component.PlayOrder;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.response.ConfigurationResponse;

class DemoConfigurer implements ResponseListener<ConfigurationResponse> {

    private final PlayOrder playOrder;
    private final PlayerType shouldWin;
    private final WinSequence winSequence;
    private final BoardDimensions dimensions;

    DemoConfigurer(PlayOrder playOrder, PlayerType shouldWin, WinSequence winSequence, BoardDimensions dimensions) {
        this.playOrder = playOrder;
        this.shouldWin = shouldWin;
        this.winSequence = winSequence;
        this.dimensions = dimensions;
    }

    @Override
    public void accept(ConfigurationResponse response) {
        DataBus<GameEvent> gameBus = response.gameBus().orElseThrow();
        Bot bot = new Bot(gameBus, playOrder, shouldWin, winSequence, dimensions);
        gameBus.subscribe(bot);
        bot.play();
    }
}
