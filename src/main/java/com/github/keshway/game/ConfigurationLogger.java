package com.github.keshway.game;

import com.github.keshway.databus.EventLogger;
import com.github.keshway.databus.event.ConfigurationEvent;
import org.tinylog.Logger;

class ConfigurationLogger implements EventLogger<ConfigurationEvent> {
    @Override
    public void accept(ConfigurationEvent event) {
        Logger.info(event.configuration());
    }
}
