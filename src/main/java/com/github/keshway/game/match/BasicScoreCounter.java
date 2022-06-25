package com.github.keshway.game.match;

import com.github.keshway.databus.component.PlayerScore;
import com.github.keshway.databus.component.PlayerScores;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.component.ResultType;

import java.util.Map;
import java.util.function.Consumer;

class BasicScoreCounter implements ScoreCounter {

    private final MatchScores matchScores;
    private final Map<ResultType, Consumer<PlayerType>> counters;

    BasicScoreCounter() {
        matchScores = new MatchScores();
        counters = Map.of(
                ResultType.WIN, this::countWin,
                ResultType.DEFEAT, this::countDefeat,
                ResultType.DRAW, this::countDraw
        );
    }

    @Override
    public PlayerScores countScore(PlayerType player, ResultType result) {
        if (result.equals(ResultType.DRAW)) {
            counters.get(result).accept(player);
            counters.get(result).accept(player.getOpponent());
            return result();
        }
        counters.get(result).accept(player);
        return result();
    }

    private void countWin(PlayerType player) {
        matchScores.update(player, ResultType.WIN);
    }

    private void countDefeat(PlayerType player) {
        matchScores.update(player, ResultType.DEFEAT);
    }

    private void countDraw(PlayerType player) {
        matchScores.update(player, ResultType.DRAW);
    }

    private PlayerScores result() {
        PlayerScore playerX = matchScores.playerScore(PlayerType.PLAYER_X);
        PlayerScore playerO = matchScores.playerScore(PlayerType.PLAYER_O);
        return new PlayerScores(playerX, playerO);
    }


    @Override
    public ScoreCounter reset() {
        return new BasicScoreCounter();
    }
}
