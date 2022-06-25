package com.github.keshway.game;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.GameConfiguration;
import com.github.keshway.databus.component.Result;
import com.github.keshway.databus.event.GameEvent;

public interface Initializable {
    Result initialize(GameConfiguration configuration, DataBus<GameEvent> gameBus);
}
