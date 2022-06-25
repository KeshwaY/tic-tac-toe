package com.github.keshway.databus.event;

import com.github.keshway.databus.BaseRepliableEvent;
import com.github.keshway.databus.ResponseListener;
import com.github.keshway.databus.component.GameConfiguration;
import com.github.keshway.databus.response.ConfigurationResponse;

final public class ConfigurationEvent extends BaseRepliableEvent<ConfigurationResponse> {
    private final GameConfiguration gameConfiguration;

    public ConfigurationEvent(GameConfiguration gameConfiguration, ResponseListener<ConfigurationResponse> responseListener) {
        super(responseListener);
        this.gameConfiguration = gameConfiguration;
    }

    public GameConfiguration configuration() {
        return gameConfiguration;
    }

}
