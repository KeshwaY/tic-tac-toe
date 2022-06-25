package com.github.keshway.demo;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.event.ConfigurationEvent;

public interface DemoFacade {
    void initialize(DataBus<ConfigurationEvent> configurationBus, String[] args);
}
