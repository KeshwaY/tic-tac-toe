package com.github.keshway.game.match;

import com.github.keshway.databus.component.PlayerScore;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.component.ResultType;
import com.github.keshway.databus.component.Score;

import java.util.HashMap;
import java.util.Map;

class MatchScores {
    private final Map<PlayerType, Score> scores;

    MatchScores() {
        // TODO: factory
        scores = new HashMap<>();
        scores.put(PlayerType.PLAYER_X, new Score(0));
        scores.put(PlayerType.PLAYER_O, new Score(0));
    }

    void update(PlayerType player, ResultType resultType) {
        Score currentScore = scores.get(player);
        Score newValue = currentScore.add(resultType.value());
        scores.put(player, newValue);
        new PlayerScore(player, newValue);
    }

    PlayerScore playerScore(PlayerType player) {
        return new PlayerScore(
                player, scores.get(player)
        );
    }
}
