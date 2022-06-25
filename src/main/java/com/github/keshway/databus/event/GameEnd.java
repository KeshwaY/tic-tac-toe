package com.github.keshway.databus.event;

import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.response.GameEventResponse;

final public class GameEnd extends GameEvent {
    public GameEnd(ResponseListener<GameEventResponse> responseListener) {
        super(responseListener);
    }
}
