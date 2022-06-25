package com.github.keshway.game.match;

import com.github.keshway.databus.component.PlayerScores;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.component.ResultType;

interface ScoreCounter {
    PlayerScores countScore(PlayerType player, ResultType result);
    ScoreCounter reset();
}
