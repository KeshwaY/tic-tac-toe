package com.github.keshway.databus.event;

import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.component.PlayerScores;
import com.github.keshway.databus.response.GameEventResponse;

final public class RoundResult extends GameEvent {
    private final PlayerScores playerScores;

    public RoundResult(PlayerScores playerScores, ResponseListener<GameEventResponse> responseListener) {
        super(responseListener);
        this.playerScores = playerScores;
    }

    public PlayerScores getPlayerScores() {
        return playerScores;
    }

    @Override
    public String toString() {
        return "RoundResult{" +
                "playerScores=" + playerScores +
                '}';
    }
}
