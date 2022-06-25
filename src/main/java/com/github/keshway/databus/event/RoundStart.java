package com.github.keshway.databus.event;

import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.response.GameEventResponse;

final public class RoundStart extends GameEvent {
    public RoundStart(ResponseListener<GameEventResponse> responseListener) {
        super(responseListener);
    }
}
