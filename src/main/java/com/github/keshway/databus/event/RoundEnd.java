package com.github.keshway.databus.event;

import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.component.ResultType;
import com.github.keshway.databus.response.GameEventResponse;

final public class RoundEnd extends GameEvent {
    private final PlayerType player;
    private final ResultType result;
    public RoundEnd(PlayerType player, ResultType result, ResponseListener<GameEventResponse> responseListener) {
        super(responseListener);
        this.player = player;
        this.result = result;
    }

    public PlayerType player() {
        return player;
    }

    public ResultType result() {
        return result;
    }

    @Override
    public String toString() {
        return "RoundEnd{" +
                "player=" + player +
                ", result=" + result +
                '}';
    }
}
