package com.github.keshway.databus.event;

import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.response.GameEventResponse;

final public class PlayerMove extends GameEvent {
    private final Index index;
    private final PlayerType player;

    public PlayerMove(Index index, PlayerType player, ResponseListener<GameEventResponse> responseListener) {
        super(responseListener);
        this.index = index;
        this.player = player;
    }

    public Index index() {
        return index;
    }

    public PlayerType player() {
        return player;
    }

    @Override
    public String toString() {
        return "PlayerMove{" +
                "index=" + index +
                ", player=" + player +
                '}';
    }
}
