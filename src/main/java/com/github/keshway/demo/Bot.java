package com.github.keshway.demo;

import com.github.keshway.databus.ComparableEventListener;
import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.component.BoardDimensions;
import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.PlayOrder;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.event.GameEvent;
import com.github.keshway.databus.event.PlayerMove;
import com.github.keshway.databus.event.RoundResult;
import com.github.keshway.databus.response.GameEventResponse;
import com.github.keshway.ui.GameView;

import java.util.List;
import java.util.Random;

class Bot implements ComparableEventListener<GameEvent>, ResponseListener<GameEventResponse> {
    private final DataBus<GameEvent> bus;
    private PlayerType currentPlayer;
    private final PlayerType shouldWin;
    private final List<Index> winSequence;
    private final BoardDimensions dimensions;
    private final GameView gameView;
    private int winnerIndex = 0;
    private boolean simulate = true;

    Bot(DataBus<GameEvent> gameBus, PlayOrder playOrder, PlayerType shouldWin, WinSequence winSequence, BoardDimensions dimensions) {
        this.bus = gameBus;
        this.currentPlayer = playOrder.whoStarts();
        this.shouldWin = shouldWin;
        this.winSequence = winSequence.sequence().stream().toList();
        this.dimensions = dimensions;
        this.gameView = new GameView(dimensions);
    }

    public void play() {
        while (simulate) {
            gameView.render();
            Index move = currentPlayer.equals(shouldWin) ? getWinnerMove() : getOpponentMove();
            PlayerMove playerMove = new PlayerMove(move, currentPlayer, this);
            gameView.registerMove(move, currentPlayer);
            bus.publish(playerMove);
            currentPlayer = currentPlayer.getOpponent();
        }
        gameView.render();
    }

    public Index getOpponentMove() {
        int move = new Random().nextInt(0, dimensions.size().value());
        Index index = new Index(move);
        if (winSequence.contains(index)) return getOpponentMove();
        return index;
    }

    public Index getWinnerMove() {
        Index index = winSequence.get(winnerIndex);
        winnerIndex++;
        return index;
    }

    @Override
    public void accept(GameEvent event) {
        if (event.getClass().equals(RoundResult.class)) {
            var result = (RoundResult) event;
            simulate = false;
            System.out.println(result);
        }
    }

    @Override
    public void accept(GameEventResponse response) {
        System.out.println("Valid move");
    }

    @Override
    public int compareTo(ComparableEventListener<GameEvent> o) {
        return 99;
    }
}
