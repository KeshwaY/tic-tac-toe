package com.github.keshway.databus.response;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.Result;
import com.github.keshway.databus.event.GameEvent;

import java.util.Optional;

public record ConfigurationResponse(Result result, Optional<DataBus<GameEvent>> gameBus) {
}
