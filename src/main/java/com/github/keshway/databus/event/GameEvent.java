package com.github.keshway.databus.event;

import com.github.keshway.databus.BaseRepliableEvent;
import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.response.GameEventResponse;

public abstract class GameEvent extends BaseRepliableEvent<GameEventResponse> {
    GameEvent(ResponseListener<GameEventResponse> responseListener) {
        super(responseListener);
    }
}
